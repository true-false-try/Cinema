package com.logic.cinema.controller;


import com.logic.cinema.dto.HallDTO;
import com.logic.cinema.exeptions.AddException;
import com.logic.cinema.exeptions.DeleteException;
import com.logic.cinema.exeptions.UpdateException;
import com.logic.cinema.mapper.MapStructMapper;
import com.logic.cinema.model.Hall;
import com.logic.cinema.service.HallService;
import lombok.AllArgsConstructor;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/halls")
@AllArgsConstructor
public class HallController {
    private final HallService hallService;

    private final MapStructMapper mapper;

    @GetMapping
    public List<HallDTO> allHalls(){
        return mapper.listHallsDto(hallService.findAllHalls());
    }
    @GetMapping("{id}")
    public HallDTO findHallById(@PathVariable(value = "id") Long id) {
        Hall hall = hallService.findById(id).get();
        return mapper.hallDto(hall);
    }
    @PostMapping
    public ResponseEntity<HallDTO> addHall(@RequestBody Hall hall) throws AddException {
        return ResponseEntity.ok().body(mapper.hallDto(hallService.save(hall)));
    }

    @PutMapping
    public ResponseEntity<HallDTO> updateHall(@RequestBody Hall hall) throws UpdateException, PSQLException {
        return ResponseEntity.ok().body(mapper.hallDto(hallService.update(hall)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteHall(@PathVariable(value = "id") Long id) throws DeleteException {
        return ResponseEntity.ok().body(hallService.delete(id).toString());
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
    public String getDeleteExceptionMessage(NoSuchElementException exception){
        return exception.getMessage();
    }



}
