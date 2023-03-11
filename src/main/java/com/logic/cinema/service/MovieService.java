package com.logic.cinema.service;

import com.logic.cinema.dto.MovieDTO;
import com.logic.cinema.exeptions.DeleteException;
import com.logic.cinema.exeptions.SaveException;
import com.logic.cinema.exeptions.UpdateException;
import com.logic.cinema.model.Movie;
import org.postgresql.util.PSQLException;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    MovieDTO save(Movie movie) throws SaveException;
    MovieDTO update(Movie movie) throws UpdateException, PSQLException;
    void delete(Long id) throws DeleteException;

    List<MovieDTO> findAllMovies();
    Optional<Movie> findMovieById(Long id);
    Optional<Movie> findMovieByName(String name);
    MovieDTO dtoFindById(Long id);
    MovieDTO dtoFindByName(String name);
}
