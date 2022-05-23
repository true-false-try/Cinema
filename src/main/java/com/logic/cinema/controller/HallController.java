package com.logic.cinema.controller;


import com.logic.cinema.model.Hall;
import com.logic.cinema.model.HallsList;
import com.logic.cinema.model.Seat;
import com.logic.cinema.service.HallService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/halls")
public class HallController {

    private HallService hallService;

    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    @GetMapping()
    public List<Hall> allHalls(){
        return hallService.findAllHalls();
    }

    @PostMapping("/add")
    public String addHall(@RequestBody Hall hall) { return hallService.save(hall); }

    @PutMapping("/update")
    public String updateHall(@RequestBody Hall hall) {
        hallService.update(hall);
        return String.format("Hall %s was updated", hall.getId());
    }

    @DeleteMapping("/delete/{id}")
    public String deleteHall(@PathVariable(value = "id") Long id) throws RuntimeException {
        return hallService.delete(id);
    }

}
