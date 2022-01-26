package com.logic.cinema.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 128, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "min_age", length = 2)
    private Integer minAge;

    @Column(name = "rating", length = 3, nullable = false)
    private Double rating;

    @Column(name = "type", length = 50, nullable = false)
    private String type;

    @Column(name = "genres", length = 50, nullable = false)
    private String genres;

    @Column(name = "date_of_start_showing")
    private Date dateOfStartShowing;

    @Column(name = "date_of_end_showing")
    private Date dateOfEndShowing;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "timeslots",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "hall_id")}
    )
    private Set<Hall> halls;

    public Movie() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public Date getDateOfStartShowing() {
        return dateOfStartShowing;
    }

    public void setDateOfStartShowing(Date dateOfStartShowing) {
        this.dateOfStartShowing = dateOfStartShowing;
    }

    public Date getDateOfEndShowing() {
        return dateOfEndShowing;
    }

    public void setDateOfEndShowing(Date dateOfEndShowing) {
        this.dateOfEndShowing = dateOfEndShowing;
    }

    public Set<Hall> getTimeSlot() {
        return halls;
    }

    public void setTimeSlot(Set<Hall> hall) {
        this.halls = hall;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", minAge=" + minAge +
                ", rating=" + rating +
                ", type='" + type + '\'' +
                ", genres='" + genres + '\'' +
                ", dateOfStartShowing=" + dateOfStartShowing +
                ", dateOfEndShowing=" + dateOfEndShowing +
                ", timeSlot=" + halls +
                '}';
    }
}

