package com.logic.cinema.repository;

import com.logic.cinema.model.Seat;
import com.logic.cinema.model.constant.StatusSeatsList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface SeatDAO extends JpaRepository<Seat,Long> {
    Set<Seat> findSeatsByHallId(Long hallId);
    Set<Seat> findSeatsByHallIdAndStatus(Long hallId, StatusSeatsList status);
    Optional<Seat> findSeatsByRowAndSeat(Integer row, Integer seat);
    Optional<Seat> findSeatByIdAndHallId(Long seatId, Long hallId);



}
