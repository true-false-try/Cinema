package com.logic.cinema.service;

import com.logic.cinema.model.Seat;

import java.util.List;
import java.util.Set;

public interface SeatService {
    List<Seat> findAllSeats();
    Seat save(Seat hall);
    Set<Seat> findByHall(Long id);
    Set<Seat> saveAll(Set<Seat> seats);
}
