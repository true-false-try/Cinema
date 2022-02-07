package com.logic.cinema.dao;

import com.logic.cinema.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatDAO extends JpaRepository<Seat,Long> {
}
