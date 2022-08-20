package com.logic.cinema.service;

import com.logic.cinema.exeptions.AddException;
import com.logic.cinema.exeptions.DeleteException;
import com.logic.cinema.exeptions.UpdateException;
import com.logic.cinema.model.Hall;
import com.logic.cinema.model.Seat;
import org.json.JSONObject;
import org.postgresql.util.PSQLException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

public interface SeatService {
    List<Seat> findAllSeats();
    Optional<Seat> findById(Long id);
    Set<Seat> findSeatsByHallId(Long id);
    Set<Seat> save(Hall hall) throws UpdateException, NoSuchElementException;
    Set<Seat> saveAll(Set<Seat> seats);
    Seat update(Hall hall, Long id) throws UpdateException;
    JSONObject delete(Long id) throws DeleteException;


}
