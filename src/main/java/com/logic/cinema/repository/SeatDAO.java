package com.logic.cinema.repository;

import com.logic.cinema.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SeatDAO extends JpaRepository<Seat,Long> {
    @Query("select s from Seat s where s.hall.id = :hall_id")
    Set<Seat> getSeatByHall(Long hall_id);
}
