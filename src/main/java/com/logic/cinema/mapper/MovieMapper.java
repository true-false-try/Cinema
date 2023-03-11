package com.logic.cinema.mapper;

import com.logic.cinema.dto.MovieDTO;
import com.logic.cinema.model.Movie;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MovieMapper {
    MovieDTO toMovieDTO(Movie movie);
    List<MovieDTO> toMovieDTO(List<Movie> movie);
}
