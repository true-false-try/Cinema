package com.logic.cinema.controller;


import com.logic.cinema.exeptions.DeleteException;
import com.logic.cinema.exeptions.UpdateException;
import com.logic.cinema.model.Hall;
import com.logic.cinema.service.HallService;
import org.postgresql.util.PSQLException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public Hall addHall(@RequestBody Hall hall) { return hallService.save(hall); }

    @PutMapping
    public Hall updateHall(@RequestBody Hall hall) throws UpdateException, PSQLException {
        return hallService.update(hall);
    }

    @DeleteMapping("{id}")
    public String deleteHall(@PathVariable(value = "id") Long id) throws DeleteException {
        return hallService.delete(id);
    }

}
