package com.logic.cinema.controller;

import com.logic.cinema.model.Ticket;
import com.logic.cinema.service.TicketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/list")
    public List<Ticket> allTicket(){
        return ticketService.findAllTicket();
    }



}
