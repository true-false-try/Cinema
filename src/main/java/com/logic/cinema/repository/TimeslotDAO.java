package com.logic.cinema.repository;

import com.logic.cinema.model.Timeslot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TimeslotDAO extends JpaRepository<Timeslot,Long>, CrudRepository<Timeslot, Long> {
    Optional<Timeslot> findByMovieId(Long id);
}
