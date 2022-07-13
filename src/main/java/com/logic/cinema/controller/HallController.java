package com.logic.cinema.controller;


import com.logic.cinema.exeptions.AddException;
import com.logic.cinema.exeptions.DeleteException;
import com.logic.cinema.exeptions.UpdateException;
import com.logic.cinema.model.Hall;
import com.logic.cinema.service.HallService;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/halls")
@AllArgsConstructor
public class HallController {
    private HallService hallService;

    @GetMapping()
    public List<Hall> allHalls(){
        return hallService.findAllHalls();
    }

    @PostMapping
    public ResponseEntity<Hall> addHall(@RequestBody Hall hall) throws AddException {
        return ResponseEntity.ok().body(hallService.save(hall));
    }

    @PutMapping
    public ResponseEntity<Hall> updateHall(@RequestBody Hall hall) throws UpdateException, PSQLException {
        return ResponseEntity.ok().body(hallService.update(hall));
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

}
