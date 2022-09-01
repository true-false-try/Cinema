package com.logic.cinema.service;


import com.logic.cinema.dto.SeatDTO;
import com.logic.cinema.exeptions.DeleteException;
import com.logic.cinema.exeptions.UpdateException;
import com.logic.cinema.model.Hall;
import com.logic.cinema.model.Seat;
import org.json.JSONObject;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

public interface SeatService {
    List<Seat> findAllSeats();
    Optional<Seat> findById(Long id);
    Set<Seat> findSeatsByHallId(Long id);
    Set<SeatDTO> save(Hall hall) throws UpdateException, NoSuchElementException;
    Set<Seat> saveAll(Set<Seat> seats);
    SeatDTO update(Hall hall, Long id) throws UpdateException;
    void delete(Long id) throws DeleteException;

}
