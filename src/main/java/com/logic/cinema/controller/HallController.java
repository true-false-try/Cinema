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
    public Hall addHall(@RequestBody Hall hall) { return hallService.save(hall); }

    @PutMapping("/update/{id}")
    public String updateHall(@PathVariable(value = "id") Long id, @RequestParam HallsList name) {
        try {
            Hall hall = hallService.findById(id);
            hallService.update(hall.getId(), name);
        }
        catch (RuntimeException exception) {
            return "Hall not found for id :" + id;
        }
        return String.format("Hall id: %d have been updating",id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteHall(@PathVariable(value = "id") Long id) {
        try {
            hallService.delete(id);
        }
        catch (RuntimeException exception) {
            return "Hall not found for id :  " + id;
        }
        return String.format("Hall %s have been deleted",id);
    }

}
