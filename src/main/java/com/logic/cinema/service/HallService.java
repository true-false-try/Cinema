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
    String save(Hall hall);
    String update(Hall hall);
    List<Hall> findAllHalls();
    Optional<Hall> findById(Long id);
    String delete(Long id);
}
