package com.logic.cinema.service.impl;

import com.logic.cinema.model.Hall;
import com.logic.cinema.model.Seat;
import com.logic.cinema.repository.SeatDAO;
import com.logic.cinema.service.SeatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class SeatServiceImpl implements SeatService {
    private final SeatDAO seatDAO;

    @Override
    public List<Seat> findAllSeats() {
        return seatDAO.findAll();
    }

    @Override
    public Seat save(Seat seat) {
        return seatDAO.save(seat);
    }

    @Override
    public Set<Seat> findByHall(Long id) {
        return seatDAO.getSeatByHall(id);
    }

    @Override
    public Set<Seat> saveAll(Set<Seat> seats) {
        List<Seat> listSet = seatDAO.saveAll(seats);
        return new HashSet<>(listSet);
    }

}
