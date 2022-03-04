package com.logic.cinema.dao;

import com.logic.cinema.model.Timeslot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeslotDAO extends JpaRepository<Timeslot,Long> {
}
