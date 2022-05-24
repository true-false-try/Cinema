package com.logic.cinema.service.impl;

import com.logic.cinema.repository.TicketDAO;
import com.logic.cinema.model.Ticket;
import com.logic.cinema.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    public final TicketDAO ticketDAO;

    public TicketServiceImpl(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    @Override
    public List<Ticket> findAllTicket() {
        return ticketDAO.findAll();
    }
}
