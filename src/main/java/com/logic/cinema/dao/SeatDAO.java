package com.logic.cinema.dao;

import com.logic.cinema.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SeatDAO extends JpaRepository<Seat,Long> {
    @Query("SELECT s FROM Seat s WHERE s.hall.id = :hallId")
    Set<Seat> findByHallId(@Param("hallId") Long hallId);

}
