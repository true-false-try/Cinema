package com.logic.cinema.controller;

import com.logic.cinema.model.Timeslot;
import com.logic.cinema.service.impl.TimeslotServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/timeslot")
@AllArgsConstructor
public class TimeslotController {
    private final TimeslotServiceImpl timeslotService;

    @GetMapping("/list")
    public List<Timeslot> allTimeslots(){
        return timeslotService.findAllTimeslots();
    }
}
