package com.logic.cinema.service.impl;

import com.logic.cinema.dto.SeatDTO;
import com.logic.cinema.exeptions.FindException;
import com.logic.cinema.exeptions.UpdateException;
import com.logic.cinema.mapper.SeatMapper;
import com.logic.cinema.model.*;
import com.logic.cinema.model.constant.StatusSeatsList;
import com.logic.cinema.repository.TicketDAO;
import com.logic.cinema.service.*;
import com.logic.cinema.util.JsonCreateTicket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    public final TicketDAO ticketDAO;

    public final HallService hallService;
    public final SeatService seatService;
    public final MovieService movieService;
    public final TimeslotService timeslotService;
    public final TariffService tariffService;

    public final UserService userService;

    public final SeatMapper seatMapper;

    @Override
    public List<Ticket> findAllTicket() {
        return ticketDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Set<SeatDTO> findAllFreeSeats(String movieName) throws FindException {
        Optional<Movie> optionalMovie = movieService.findMovieByName(movieName);

        if (optionalMovie.isPresent()) {
            Optional<Timeslot> optionalTimeslot = timeslotService.findTimeslotByMovieId(optionalMovie.get().getId());

            if (optionalTimeslot.isPresent()) {
                Long hallId = hallService.findById(optionalTimeslot.get().getHall().getId()).get().getId();
                Set<Seat> freeSeats = seatService.findSeatsByHallIdAndStatus(hallId, StatusSeatsList.NOT_AVAILABLE);

                if (!freeSeats.isEmpty()) {
                    return seatMapper.toListSeatsDTO(freeSeats);
                } else throw new FindException("All seats are occupied");

            } else throw new FindException("The film is not running today");

        } else throw new FindException("Movie doesn't find");

    }

    @Override
    @Transactional
    public Ticket createTicket(JsonCreateTicket jsonCreateTicket) throws FindException {
        Optional<Movie> movieOptional = movieService.findMovieByName(jsonCreateTicket.getMovieName());

        if (movieOptional.isPresent()) {
            Optional<User> optionalUser = userService.findByLoginAndPassword(jsonCreateTicket.getLogin(), jsonCreateTicket.getPassword());

            if (optionalUser.isPresent()) {
               Timeslot timeslot = timeslotService.findTimeslotByMovieId(2L).get();

               Set<Seat> seats = seatService.findSeatsByHallId(timeslot.getHall().getId())
                        .stream()
                        .filter(v -> v.getStatus().equals(StatusSeatsList.AVAILABLE) &&
                                v.getRow().equals(jsonCreateTicket.getRow())
                                && v.getSeat().equals(jsonCreateTicket.getSeat()))
                       .peek(v -> v.setStatus(StatusSeatsList.NOT_AVAILABLE))
                       .collect(Collectors.toSet());

               seats.forEach(v -> {
                   try {
                       seatService.update(timeslot.getHall(), v.getId());
                   } catch (UpdateException e) {
                       throw new RuntimeException(e);
                   }
               });

               Tariff tariff = tariffService.save(
                       Tariff.builder()
                               .type(jsonCreateTicket.getType())
                               .cost(jsonCreateTicket.getCost())
                               .build());
               
               Ticket ticket =  ticketDAO.save(Ticket.builder()
                        .tariff(tariff)
                        .user(optionalUser.get())
                        // TODO this par must use not 2L as timeslot
                        .timeslot(timeslot)
                        .build());

               return ticket;
            } else throw new  FindException("User didn't register");

        } else throw new FindException("Movie doesn't find");
    }

    @Override
    public Ticket save(Ticket ticket) {
        return ticketDAO.save(ticket);
    }

}
