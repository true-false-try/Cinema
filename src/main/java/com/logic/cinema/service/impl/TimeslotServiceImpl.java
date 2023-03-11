package com.logic.cinema.service.impl;

import com.logic.cinema.model.Timeslot;
import com.logic.cinema.repository.TimeslotDAO;
import com.logic.cinema.service.TimeslotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TimeslotServiceImpl implements TimeslotService {
    private final TimeslotDAO repository;

    @Override
    public Optional<Timeslot> findTimeslotByMovieId(Long id) {

        return repository.findByMovieId(id);
    }
}
