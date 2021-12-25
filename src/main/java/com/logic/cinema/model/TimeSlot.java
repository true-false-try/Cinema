package com.logic.cinema.model;

import javax.persistence.*;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "timeslots")
public class TimeSlot {

    @Transient
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.M.yyyy");

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "start_time", length = 10, nullable = false)
    private Time startTime;

    @Column(name = "end_time", length = 10, nullable = false)
    private Time endTime;

    @OneToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "timeSlot")
    private Set<Movie> movies;

    public TimeSlot(){

    }

    public SimpleDateFormat getSimpleDateFormat() {
        return simpleDateFormat;
    }

    public void setSimpleDateFormat(SimpleDateFormat simpleDateFormat) {
        this.simpleDateFormat = simpleDateFormat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "TimeSlot{" +
                "simpleDateFormat=" + simpleDateFormat +
                ", id='" + id + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", hall=" + hall +
                ", movies=" + movies +
                '}';
    }
}
