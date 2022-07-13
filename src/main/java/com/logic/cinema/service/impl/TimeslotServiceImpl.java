package com.logic.cinema.service.impl;

import com.logic.cinema.repository.TimeslotDAO;
import com.logic.cinema.model.Timeslot;
import com.logic.cinema.service.TimeslotService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TimeslotServiceImpl implements TimeslotService {
    private final TimeslotDAO timeslotDAO;

    @Override
    public List<Timeslot> findAllTimeslots() {
        return timeslotDAO.findAll();
    }
}
