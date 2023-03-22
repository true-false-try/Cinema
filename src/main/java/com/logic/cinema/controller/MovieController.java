package com.logic.cinema.controller;

import com.logic.cinema.dto.MovieDTO;
import com.logic.cinema.exeptions.DeleteException;
import com.logic.cinema.exeptions.SaveException;
import com.logic.cinema.exeptions.UpdateException;
import com.logic.cinema.model.Movie;
import com.logic.cinema.service.MovieService;
import lombok.AllArgsConstructor;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PostMapping
    @PreAuthorize("hasAuthority('all:write')")
    public ResponseEntity<MovieDTO> createMovie(@RequestBody Movie movie) throws SaveException{
        return ResponseEntity.ok().body(movieService.save(movie));
    }
    @PutMapping
    @PreAuthorize("hasAuthority('all:write')")
    public ResponseEntity<MovieDTO> updateMovie(@RequestBody Movie movie) throws UpdateException, PSQLException {
        return ResponseEntity.ok().body(movieService.update(movie));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('all:write')")
    public void deleteMovie(@PathVariable(value = "id") Long id) throws DeleteException {
        movieService.delete(id);
    }

    @ExceptionHandler({SaveException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String getSaveExceptionMessage(SaveException exception) {
        return String.format(exception.getMessage());
    }
    @ExceptionHandler({UpdateException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String getUpdateExceptionMessage(UpdateException exception) {
        return String.format(exception.getMessage());
    }
    @ExceptionHandler({DeleteException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String getDeleteExceptionMessage(DeleteException exception){
        return exception.createJsonMessage(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
    @ExceptionHandler({PSQLException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String getPSQLExceptionMessage(PSQLException exception){
        return exception.getMessage();
    }
    @ExceptionHandler({NoSuchElementException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String getNoSuchElementExceptionMessage(NoSuchElementException exception) {
        return String.format(exception.getMessage());
    }

}
