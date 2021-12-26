package com.logic.cinema.model;

import javax.persistence.*;

@Entity
@Table(name = "seats")
public class Seat {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "row", length = 3, nullable = false)
    private int row;

    @Column(name = "seat", length = 3, nullable = false)
    private int seat;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 2, nullable = false)
    private StatusSeatsList status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hall_id")
    private Hall halls;

    public Seat() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        return halls;
    }

    public void setHalls(Hall halls) {
        this.halls = halls;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", row=" + row +
                ", seat=" + seat +
                ", status=" + status +
                ", halls=" + halls +
                '}';
    }
}
