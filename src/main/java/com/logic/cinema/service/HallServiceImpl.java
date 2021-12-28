package com.logic.cinema.service;

import com.logic.cinema.model.Seat;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class HallServiceImpl implements HallService{


    @Override
    public Set<Seat> showAvalibleSeat() {
        return null;
    }

    @Override
    public String statusHall(String hallName) {
        return null;
    }

    public HallServiceImpl() {

    }
}
