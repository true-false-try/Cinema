package com.logic.cinema.controller;

import com.logic.cinema.dto.SeatDTO;
import com.logic.cinema.exeptions.AddException;
import com.logic.cinema.exeptions.DeleteException;
import com.logic.cinema.exeptions.UpdateException;
import com.logic.cinema.model.Hall;
import com.logic.cinema.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
public class SeatController {
    private final SeatService seatService;
    @GetMapping
    public List<SeatDTO> allSeats() {
        return seatService.dtoFindAllSeats();
    }

    @GetMapping("{id}")
    public SeatDTO findSeatById(@PathVariable(value = "id") Long id) {
        return seatService.dtoFindById(id);
    }

    @GetMapping("{id}/hall")
    public List<SeatDTO> findSeatByHallId(@PathVariable(value = "id") Long id) {
        return new ArrayList<>(seatService.dtoFindSeatsByHallId(id));
    }

    @PostMapping
    public ResponseEntity<List<SeatDTO>> addSeats(@RequestBody Hall hall) throws UpdateException {
        return ResponseEntity.ok(
                new ArrayList<>(seatService.save(hall)));
    }

    @PutMapping("{id}")
    public ResponseEntity<List<SeatDTO>> updateSeat(@PathVariable(value = "id") Long id,
                                                    @RequestBody Hall hall) throws UpdateException {
        return ResponseEntity.ok(
                new ArrayList<>(Collections.singleton(seatService.update(hall, id))));
    }

    @DeleteMapping("{id}")
    public void deleteSeat(@PathVariable(value = "id") Long id) throws DeleteException {
        seatService.delete(id);

    }

    @ExceptionHandler({DeleteException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String getDeleteExceptionMessage(DeleteException exception) {
        return exception.createJsonMessage(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler({AddException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String getAddExceptionMessage(AddException exception) {
        return exception.createJsonMessage(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler({UpdateException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String getUpdateExceptionMessage(UpdateException exception) {
        return exception.createJsonMessage(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler({NoSuchElementException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String getDeleteExceptionMessage(NoSuchElementException exception) {
        return String.format(exception.getMessage());
    }


}
