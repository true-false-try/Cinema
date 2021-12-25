package com.logic.cinema.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "halls")
public class Hall {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @OneToMany
    private Set<Seat> seats;

    public Hall(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
