package com.logic.cinema.model;

import javax.persistence.*;
import java.sql.Time;
import java.text.SimpleDateFormat;

@Entity
@Table(name = "timeslots")
public class TimeSlot {

    @Transient
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.M.yyyy");

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time", length = 10, nullable = false)
    private Time startTime;

    @Column(name = "end_time", length = 10, nullable = false)
    private Time endTime;

    @OneToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @OneToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    public TimeSlot(){

    }


    public SimpleDateFormat getSimpleDateFormat() {
        return simpleDateFormat;
    }

    public void setSimpleDateFormat(SimpleDateFormat simpleDateFormat) {
        this.simpleDateFormat = simpleDateFormat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "TimeSlot{" +
                "simpleDateFormat=" + simpleDateFormat +
                ", id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", hall=" + hall +
                ", movie=" + movie +
                ", ticket=" + ticket +
                '}';
    }
}
