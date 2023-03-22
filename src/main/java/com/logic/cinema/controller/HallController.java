package com.logic.cinema.controller;


import com.logic.cinema.dto.HallDTO;
import com.logic.cinema.exeptions.AddException;
import com.logic.cinema.exeptions.DeleteException;
import com.logic.cinema.exeptions.UpdateException;
import com.logic.cinema.model.Hall;
import com.logic.cinema.model.HallsName;
import com.logic.cinema.service.HallService;
import lombok.RequiredArgsConstructor;
import org.postgresql.util.PSQLException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/halls")
@RequiredArgsConstructor
public class  HallController {
    private final HallService hallService;

    @GetMapping
    public List<HallDTO> allHalls(){
        return hallService.findAllHalls();
    }
    @GetMapping("{id}")
    public HallDTO findHallById(@PathVariable(value = "id") Long id) {
        return hallService.dtoFindById(id);
    }

    @GetMapping("/name/{name}")
    public HallDTO findHallByName(@PathVariable(value = "name") HallsName name) {
        return hallService.dtoFindByName(name);
    }

    @PostMapping
    public ResponseEntity<HallDTO> addHall(@RequestBody Hall hall) throws AddException {
        return ResponseEntity.ok().body(hallService.save(hall));
    }

    @PutMapping
    public ResponseEntity<HallDTO> updateHall(@RequestBody Hall hall) throws UpdateException, PSQLException {
        return ResponseEntity.ok().body(hallService.update(hall));
    }

    @DeleteMapping("{id}")
    public void deleteHall(@PathVariable(value = "id") Long id) throws DeleteException {
        hallService.delete(id);
    }


}
