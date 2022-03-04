package com.logic.cinema.service;

import com.logic.cinema.dao.TimeslotDAO;
import com.logic.cinema.model.Timeslot;
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
