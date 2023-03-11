package com.logic.cinema.service;

import com.logic.cinema.dto.SeatDTO;
import com.logic.cinema.exeptions.FindException;
import com.logic.cinema.model.Ticket;
import com.logic.cinema.util.JsonCreateTicket;

import java.util.List;
import java.util.Set;


public interface TicketService {
    List<Ticket> findAllTicket();
    Set<SeatDTO> findAllFreeSeats(String movieName) throws FindException;
    Ticket createTicket(JsonCreateTicket jsonCreateTicket) throws FindException;
    Ticket save(Ticket ticket);
}
