package com.logic.cinema.service.impl;

import com.logic.cinema.repository.TimeslotDAO;
import com.logic.cinema.model.Timeslot;
import com.logic.cinema.service.TimeslotService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeslotServiceImpl implements TimeslotService {

    private final TimeslotDAO timeslotDAO;

    public TimeslotServiceImpl(TimeslotDAO timeslotDAO) {
        this.timeslotDAO = timeslotDAO;
    }

    @Override
    public List<Timeslot> findAllTimeslots() {
        return null;
    }
}
