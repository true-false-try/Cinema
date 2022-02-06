package com.logic.cinema.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tickets")
// @JsonIdentityInfo - use for solve the problem with recursion, when we use @OneToMany @ManyToOne
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Ticket {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "tariff_id")
    private Tariff tariff;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "ticket")
    private List<Timeslot> timeSlot;

    public Ticket() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public List<Timeslot> getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(List<Timeslot> timeSlot) {
        this.timeSlot = timeSlot;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", tariff=" + tariff +
                ", timeSlot=" + timeSlot +
                '}';
    }
}



