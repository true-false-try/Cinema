package com.logic.cinema.service.impl;

import com.logic.cinema.dto.MovieDTO;
import com.logic.cinema.mapper.MovieMapper;
import com.logic.cinema.model.Movie;
import com.logic.cinema.repository.MovieDAO;
import com.logic.cinema.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieDAO movieDAO;

    private final MovieMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<MovieDTO> findAllMovies(){
        return mapper.toMovieDTO(movieDAO.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Movie> findMovieById(Long id) {
        return movieDAO.findById(id);
    }
    @Override
    public Optional<Movie> findMovieByName(String name) {
        return movieDAO.findByNameIgnoreCase(name);
    }

    @Override
    public MovieDTO dtoFindById(Long id) {
        return mapper.toMovieDTO(findMovieById(id).get());
    }

    @Override
    public MovieDTO dtoFindByName(String name) {
        return mapper.toMovieDTO(findMovieByName(name).get());
    }


}
