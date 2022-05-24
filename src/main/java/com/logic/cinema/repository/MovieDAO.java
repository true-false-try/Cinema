package com.logic.cinema.repository;

import com.logic.cinema.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieDAO extends JpaRepository<Movie,Long> {
}
