package com.logic.cinema.controller;

import com.logic.cinema.dto.SeatDTO;
import com.logic.cinema.exeptions.FindException;
import com.logic.cinema.model.Ticket;
import com.logic.cinema.service.TicketService;
import com.logic.cinema.util.JsonCreateTicket;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@RestController
@RequestMapping("/api/tickets")
@AllArgsConstructor
@Slf4j
public class TicketController {

    private final TicketService ticketService;

    @GetMapping
    public List<Ticket> allTickets(){
        return ticketService.findAllTicket();
    }
    // Use PathParam -->
    @GetMapping("free-seats")
    public Set<SeatDTO> findFreeSeats(@PathParam("movie") String movieName) throws FindException {
        return ticketService.findAllFreeSeats(movieName);
    }

    @PostMapping("create")
    public Ticket createTicket(@RequestBody JsonCreateTicket jsonCreateTicket) throws FindException {
            return ticketService.createTicket(jsonCreateTicket);
    }

    @ExceptionHandler(FindException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String getUpdateExceptionMessage(FindException exception){
        return exception.createJsonMessage(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String getUpdateExceptionMessage(NoSuchElementException exception){
        return exception.getMessage();
    }


}
