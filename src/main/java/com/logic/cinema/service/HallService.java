package com.logic.cinema.service;

import com.logic.cinema.model.Seat;

import java.util.Set;

public interface HallService {

    Set<Seat> showAvalibleSeat();

    String statusHall(String hallName);
}
