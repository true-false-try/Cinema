package com.logic.cinema.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;

@Entity
@Table(name = "seats")
// @JsonIdentityInfo - use for solve the problem with recursion, when we use @OneToMany @ManyToOne
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Seat {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "row", length = 3, nullable = false)
    private int row;

    @Column(name = "seat", length = 3, nullable = false)
    private int seat;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 2, nullable = false)
    private StatusSeatsList status;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "hall_id")
    private Hall hall;


    public Seat() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public StatusSeatsList getStatus() {
        return status;
    }

    public void setStatus(StatusSeatsList status) {
        this.status = status;
    }

    public Hall getHalls() {
        return hall;
    }

    public void setHalls(Hall halls) {
        this.hall = halls;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", row=" + row +
                ", seat=" + seat +
                ", status=" + status +
                ", halls=" + hall +
                '}';
    }
}
