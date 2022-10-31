package com.logic.cinema.service;

import com.logic.cinema.model.Timeslot;

import java.util.Optional;

public interface TimeslotService {
    Optional<Timeslot> findTimeslotByMovieId(Long id);
}
