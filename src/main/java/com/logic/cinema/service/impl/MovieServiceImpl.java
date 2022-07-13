package com.logic.cinema.service.impl;

import com.logic.cinema.repository.MovieDAO;
import com.logic.cinema.model.Movie;
import com.logic.cinema.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieDAO movieDAO;

    @Override
    public List<Movie> findAllMovies(){
        return movieDAO.findAll();
    }
}
