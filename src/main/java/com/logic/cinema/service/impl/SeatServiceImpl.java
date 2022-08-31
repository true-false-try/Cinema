package com.logic.cinema.service.impl;

import com.logic.cinema.dto.SeatDTO;
import com.logic.cinema.exeptions.DeleteException;
import com.logic.cinema.exeptions.UpdateException;
import com.logic.cinema.mapper.MapStructMapper;
import com.logic.cinema.model.Hall;
import com.logic.cinema.model.Seat;
import com.logic.cinema.repository.SeatDAO;
import com.logic.cinema.service.HallService;
import com.logic.cinema.service.SeatService;
import com.logic.cinema.util.JsonResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SeatServiceImpl implements SeatService {
    private final SeatDAO seatDAO;

    private final HallService hallService;

    private final MapStructMapper mapper;

    @Autowired
    // Solved cycle dependency with annotation @Lazy
    public SeatServiceImpl(SeatDAO seatDAO, @Lazy HallService hallService, MapStructMapper mapper) {
        this.seatDAO = seatDAO;
        this.hallService = hallService;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Seat> findAllSeats() {
        return seatDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Seat> findById(Long id) {
        return seatDAO.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Seat> findSeatsByHallId(Long id) {
        return seatDAO.findSeatsByHallId(id);
    }

    @Override
    @Transactional
    public Set<SeatDTO> save(Hall hall) throws UpdateException, NoSuchElementException {
        Optional<Hall> findHall = hallService.findById(hall.getId());
        if(findHall.isPresent()) {
            Set<Seat> savedSeats = findHall.get().getSeats();
            Set<Seat> newSeats = hall.getSeats();

            checkingForUniqueAndMaxSizeSeat(newSeats).forEach(value -> value.setHall(hall));
            savedSeats.addAll(hall.getSeats());
            seatDAO.saveAll(savedSeats);
        }

        return mapper.toListSeatsDTO(findHall.get().getSeats());
    }

    @Override
    @Transactional
    public Set<Seat> saveAll(Set<Seat> seats) {
        List<Seat> listSet = seatDAO.saveAll(seats);
        return new HashSet<>(listSet);
    }

    @Override
    @Transactional
    public SeatDTO update(Hall hall, Long seatId) throws UpdateException {
        Optional<Seat> seatFind = seatDAO.findSeatByIdAndHallId(seatId, hall.getId());
        Seat updateSeat = hall.getSeats().stream().findFirst().get();

        if (seatFind.isPresent()) {
                updateSeat.setId(seatId);
                updateSeat.setHall(hall);
            return mapper.toSeatDTO(seatDAO.save(updateSeat));
        } else throw new UpdateException("Wasn't find id number , maybe this id is wrong");
    }

    @Override
    @Transactional
    public JSONObject delete(Long id) throws DeleteException {
        if (seatDAO.findById(id).isPresent()) {
            seatDAO.deleteById(id);;
        } else throw new DeleteException("Seat not found for id: %s, please enter valid id", id);

        return JsonResponse.responseMessage(String.format("Seat %s have been deleted",id));

    }
    private Set<Seat> checkingForUniqueAndMaxSizeSeat(Set<Seat> seats) throws UpdateException {
        Set<Seat> newSeats  = new HashSet<>();

        for (Seat seat:
             seats) {
            if (seatDAO.findSeatsByRowAndSeat(seat.getRow(), seat.getSeat()).isPresent()) {
                throw new UpdateException(
                        String.format("Sorry but you have duplicate seat {row : %d, seat: %d}", seat.getRow(), seat.getSeat()));
            }
            newSeats.add(seat);
        }

        return newSeats;
    }

    /**
     * Convert in DTO methods
     * @return Object DTO or something collection that include Object DTO
     */
    public List<SeatDTO> dtoFindAllSeats() { return mapper.toListSeatsDTO(findAllSeats()); }

    public SeatDTO dtoFindById(Long id) {return mapper.toSeatDTO(findById(id).get());}

    public Set<SeatDTO> dtoFindSeatsByHallId(Long id) {
        return mapper.toListSeatsDTO(findSeatsByHallId(id));
    }

}
