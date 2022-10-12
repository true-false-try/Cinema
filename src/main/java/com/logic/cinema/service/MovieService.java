package com.logic.cinema.service;

import com.logic.cinema.dto.MovieDTO;
import com.logic.cinema.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<MovieDTO> findAllMovies();
    Optional<Movie> findMovieById(Long id);
    Optional<Movie> findMovieByName(String name);
    MovieDTO dtoFindById(Long id);
    MovieDTO dtoFindByName(String name);
}
