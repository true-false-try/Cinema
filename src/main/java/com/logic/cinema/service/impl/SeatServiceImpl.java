package com.logic.cinema.service.impl;

import com.logic.cinema.repository.SeatDAO;
import com.logic.cinema.model.Seat;
import com.logic.cinema.service.SeatService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SeatServiceImpl implements SeatService {
    private final SeatDAO seatDAO;

    public SeatServiceImpl(SeatDAO seatDAO){
        this.seatDAO = seatDAO;
    }

    @Override
    public List<Seat> findAllSeats() {
        return seatDAO.findAll();
    }

    @Override
    public Set<Seat> findByHallId(Long hallId, Long seatId) {
        Set<Seat> seats = seatDAO.findByHallId(hallId);
        Set<Long> flag = seatDAO.findByHallId(hallId).stream()
                             .map(Seat::getId)
                             .filter(value -> value.equals(seatId))
                             .collect(Collectors.toSet());
        if (flag.isEmpty()) {
            return seats;
        } else {
            throw new RuntimeException("You must change id, because this id is use");
        }
    }
}
