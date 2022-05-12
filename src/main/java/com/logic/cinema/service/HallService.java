package com.logic.cinema.service;

import com.logic.cinema.model.Hall;
import com.logic.cinema.model.HallsList;
import com.logic.cinema.model.Seat;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface HallService {
    Hall save(Hall hall);
    void update(Long id, HallsList name, Set<Seat> seats);
    List<Hall> findAllHalls();
    Hall findById(Long id);
    void delete(Long id);
}
