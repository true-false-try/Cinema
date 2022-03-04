package com.logic.cinema.dao;

import com.logic.cinema.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallDAO extends JpaRepository<Hall,Long> {

}
