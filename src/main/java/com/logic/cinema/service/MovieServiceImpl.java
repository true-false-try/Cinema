package com.logic.cinema.service;

import com.logic.cinema.dao.MovieDAO;
import com.logic.cinema.model.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService{
    private final MovieDAO movieDAO;

    public MovieServiceImpl(MovieDAO movieDAO){
        this.movieDAO = movieDAO;
    }

    @Override
    public List<Movie> findAllMovies(){
        return movieDAO.findAll();
    }
}
