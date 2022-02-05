package com.logic.cinema.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "halls")
// @JsonIdentityInfo - use for solve the problem with recursion, when we use @OneToMany @ManyToOne
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Hall {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", length = 30, nullable = false)
    private HallsList name;

    @OneToOne
    @JoinColumn(name = "timeslot_id")
    private Timeslot timeslot;


    @OneToMany(mappedBy = "hall",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

    public Timeslot getMovies() {
        return timeslot;
    }

    public void setMovies(Timeslot timeslot) {
        this.timeslot = timeslot;
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
                "id=" + id +
                ", name=" + name +
                ", timeslot=" + timeslot +
                ", seats=" + seats +
                '}';
    }
}
