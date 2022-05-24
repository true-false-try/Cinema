package com.logic.cinema.controller;

import com.logic.cinema.model.Timeslot;
import com.logic.cinema.service.impl.TimeslotServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/timeslot")
public class TimeslotController {

    private final TimeslotServiceImpl timeslotService;

    public TimeslotController(TimeslotServiceImpl timeslotService) {
        this.timeslotService = timeslotService;
    }

    @GetMapping("/list")
    public List<Timeslot> allTimeslots(){
        return timeslotService.findAllTimeslots();
    }
}
