package com.logic.cinema.controller;


import com.logic.cinema.dto.HallDTO;
import com.logic.cinema.exeptions.AddException;
import com.logic.cinema.exeptions.DeleteException;
import com.logic.cinema.exeptions.UpdateException;
import com.logic.cinema.model.Hall;
import com.logic.cinema.model.HallsList;
import com.logic.cinema.service.HallService;
import lombok.RequiredArgsConstructor;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/halls")
@RequiredArgsConstructor
public class HallController {
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
    public HallDTO findHallByName(@PathVariable(value = "name") HallsList name) {
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

    @ExceptionHandler({AddException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String getAddExceptionMessage(AddException exception){
        return exception.createJsonMessage(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler({UpdateException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String getUpdateExceptionMessage(UpdateException exception){
        return exception.createJsonMessage(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler({PSQLException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String getPSQLExceptionMessage(PSQLException exception){
        return exception.getMessage();
    }

    @ExceptionHandler({DeleteException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String getDeleteExceptionMessage(DeleteException exception){
        return exception.createJsonMessage(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
    @ExceptionHandler({NoSuchElementException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String getNoSuchElementExceptionMessage(NoSuchElementException exception){
        return exception.getMessage();
    }



}
