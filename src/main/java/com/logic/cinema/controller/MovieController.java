package com.logic.cinema.controller;

import com.logic.cinema.model.Movie;
import com.logic.cinema.service.impl.MovieServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    private final MovieServiceImpl movieService;

    public MovieController(MovieServiceImpl movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/list")
    public List<Movie> allMovies(){
        return movieService.findAllMovies();
    }
}
