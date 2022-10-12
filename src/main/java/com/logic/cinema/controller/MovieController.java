package com.logic.cinema.controller;

import com.logic.cinema.dto.MovieDTO;
import com.logic.cinema.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/movies")
@AllArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public List<MovieDTO> allMovies(){
        return movieService.findAllMovies();
    }

    @GetMapping("{id}")
    public MovieDTO findMovieById(@PathVariable(name = "id") Long id){ return movieService.dtoFindById(id); }
    @GetMapping("/name/{name}")
    public MovieDTO findMovieByName(@PathVariable(name = "name") String name){ return movieService.dtoFindByName(name); }

    @ExceptionHandler({NoSuchElementException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String getDeleteExceptionMessage(NoSuchElementException exception) {
        return String.format(exception.getMessage());
    }

}
