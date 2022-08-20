package com.logic.cinema.repository;

import com.logic.cinema.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Set;

@Repository
public interface SeatDAO extends JpaRepository<Seat,Long> {
    Set<Seat> findSeatsByHallId(Long hall_id);
    Optional<Seat> findSeatsByRowAndSeat(int row, int seat);
    Optional<Seat> findSeatByIdAndHallId(Long seatId, Long hallId);


}
