package com.logic.cinema.controller;


import com.logic.cinema.model.Hall;
import com.logic.cinema.service.HallService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class HallController {

    private HallService hallService;

    @GetMapping("/list")
    public List<Hall> allHalls(){
        return hallService.findAllHalls();

    }
}
