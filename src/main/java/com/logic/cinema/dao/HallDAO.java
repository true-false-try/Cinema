package com.logic.cinema.dao;

import com.logic.cinema.model.Hall;
import com.logic.cinema.model.HallsList;
import com.logic.cinema.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
public interface HallDAO extends JpaRepository<Hall,Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Hall h SET h.name = :name, h.seats = :seats WHERE h.id = :id")
    void update(@Param("id") Long id,
                @Param("name") HallsList name,
                @Param("seats") Set<Seat> seats);
}
