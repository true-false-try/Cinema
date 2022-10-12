package com.logic.cinema.dto;

import com.logic.cinema.model.MovieGenres;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Builder
public class MovieDTO {
    private Long id;
    private String name;
    private Integer year;
    private String description;
    private Integer minAge;
    private Double rating;
    @Enumerated(EnumType.STRING)
    private MovieGenres genre;
}
