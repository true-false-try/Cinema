package com.logic.cinema.controller;

import com.logic.cinema.model.Seat;
import com.logic.cinema.service.impl.SeatServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/seat")
@AllArgsConstructor
public class SeatController {
    private final SeatServiceImpl seatService;

    @GetMapping("/list")
    public List<Seat> allSeats(){
        return seatService.findAllSeats();
    }
}
