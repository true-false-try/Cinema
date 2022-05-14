package com.logic.cinema.service;

import com.logic.cinema.dao.HallDAO;
import com.logic.cinema.model.Hall;

import com.logic.cinema.model.HallsList;
import com.logic.cinema.model.Seat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class HallServiceImpl implements HallService{

    private final HallDAO hallDAO;

    private final SeatService seatService;

    public HallServiceImpl(HallDAO hallDAO, SeatService seatService) {
        this.hallDAO = hallDAO;
        this.seatService = seatService;
    }

    @Override
    public Hall save(Hall hall) {
        try {
            Set<Long> seatsId  = hall.getSeats()
                    .stream()
                    .map(Seat::getId)
                    .collect(Collectors.toSet());

            seatsId.forEach(value -> seatService.findByHallId(hall.getId(),value));

        } catch (RuntimeException exception) {
            // Kostil'
            return null;
        }
        return hallDAO.save(hall);
    }

    @Override
    @Transactional
    public void update(Long id, HallsList name) {
        Hall hall = findById(id);
        hallDAO.update(hall.getId(),name);
    }

    @Override
    public List<Hall> findAllHalls() {
        return hallDAO.findAll();
    }

    @Override
    public Hall findById(Long id) {
        Optional<Hall> optionalHall = hallDAO.findById(id);
        Hall hall;
        if (optionalHall.isPresent()) {
            hall = optionalHall.get();
        } else {
            throw new RuntimeException("Hall not found for id :  " + id);
        }
        return hall;
    }

    @Override
    public void delete(Long id) {
        if (hallDAO.findById(id).isPresent()){
            hallDAO.deleteById(id);
        }
        else {
            throw new RuntimeException("Hall not found for id, please enter valid id");
        }
    }

}
