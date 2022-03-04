package com.logic.cinema.service;

import com.logic.cinema.dao.TicketDAO;
import com.logic.cinema.model.Ticket;
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
