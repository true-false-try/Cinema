package com.logic.cinema.model;

import com.logic.cinema.model.constant.MovieGenres;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "movies")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 128, nullable = false)
    private String name;

    @Column(name = "year", length = 4)
    private Integer year;

    @Column(name = "description")
    private String description;

    @Column(name = "min_age", length = 2)
    private Integer minAge;

    @Column(name = "rating", length = 3, nullable = false)
    private Double rating;

    @Column(name = "genre", length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private MovieGenres genre;

    @Column(name = "movie_time")
    private LocalTime movie_time;

}

