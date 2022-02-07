package com.logic.cinema.service;

import com.logic.cinema.model.Timeslot;

import java.util.List;

public interface TimeslotService {
    List<Timeslot> findAllTimeslots();
}
