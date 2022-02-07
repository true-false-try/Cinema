package com.logic.cinema.service;

import com.logic.cinema.dao.SeatDAO;
import com.logic.cinema.model.Seat;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
