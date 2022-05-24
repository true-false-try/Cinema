package com.logic.cinema.repository;

import com.logic.cinema.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketDAO extends JpaRepository<Ticket,Long> {

}
