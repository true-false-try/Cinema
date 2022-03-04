package com.logic.cinema.model;

<<<<<<< HEAD
import javax.persistence.*;

@Entity
@Table(name = "tickets")
=======
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
>>>>>>> origin/testWeb
public class Ticket {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    private Integer id;

    @OneToOne
    @Column(name = "tariff_id")
    private Tariff tariff;

    @OneToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @OneToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @OneToOne
    @JoinColumn(name = "timeSlot_id")
    private TimeSlot timeSlot;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
=======
    private Long id;

    @OneToOne
    @JoinColumn(name = "tariff_id")
    private Tariff tariff;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
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
>>>>>>> origin/testWeb
        this.id = id;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

<<<<<<< HEAD
    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "tariff=" + tariff +
                ", hall=" + hall +
                ", seat=" + seat +
                ", timeSlot=" + timeSlot +
                ", user=" + user +
=======
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
                ", user=" + user +
                ", timeSlot=" + timeSlot +
>>>>>>> origin/testWeb
                '}';
    }
}



<<<<<<< HEAD
=======



>>>>>>> origin/testWeb
