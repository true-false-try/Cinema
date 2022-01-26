package com.logic.cinema.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "halls")
public class Hall {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", length = 30, nullable = false)
    private HallsList name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "halls")
    private Set<Movie> movies;

    @OneToMany(mappedBy = "halls")
    private Set<Seat> seats;



    public Hall(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HallsList getName() {
        return name;
    }

    public void setName(HallsList name) {
        this.name = name;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Hall{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", seats=" + seats +
                '}';
    }
}
