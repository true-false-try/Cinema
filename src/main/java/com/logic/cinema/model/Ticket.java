package com.logic.cinema.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tickets")
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
                '}';
    }
}



