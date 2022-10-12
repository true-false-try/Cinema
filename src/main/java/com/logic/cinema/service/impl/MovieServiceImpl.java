package com.logic.cinema.service.impl;

import com.logic.cinema.dto.MovieDTO;
import com.logic.cinema.exeptions.DeleteException;
import com.logic.cinema.exeptions.SaveException;
import com.logic.cinema.exeptions.UpdateException;
import com.logic.cinema.mapper.MovieMapper;
import com.logic.cinema.model.Movie;
import com.logic.cinema.repository.MovieDAO;
import com.logic.cinema.service.MovieService;
import lombok.AllArgsConstructor;
import org.postgresql.util.PSQLException;
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
    @Transactional
    public MovieDTO save(Movie movie) throws SaveException{
        Optional<Movie> optionalMovie = findMovieByName(movie.getName());
        movie.setId(null);
        if (optionalMovie.isEmpty()) {
            return mapper.toMovieDTO(movieDAO.save(movie));
        } else throw new  SaveException("Hasn't saved this movie, because it's movie saved in the database as {name:%s}", movie.getName());
    }

    @Override
    @Transactional
    public MovieDTO update(Movie movie) throws UpdateException, PSQLException {
        Optional<Movie> optionalMovie = findMovieById(movie.getId());
        if (optionalMovie.isPresent()) {
            return mapper.toMovieDTO(movieDAO.save(movie));
        } else throw new  UpdateException("Movie hasn't found");

    }

    @Override
    public void delete(Long id) throws DeleteException {
        Optional<Movie> optionalMovie = findMovieById(id);
        if (optionalMovie.isPresent()) {
            movieDAO.deleteById(id);
        } else throw new DeleteException("Movie not found for id: %s, please enter valid id", id);
    }

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
    @Transactional(readOnly = true)
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
