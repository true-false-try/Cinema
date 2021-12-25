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

    @Column(name = "status", length = 2, nullable = false)
    // todo сделай это поле энамом
    private int status;

    @Column(name = "timeslot_id", length = 30, nullable = false)
    // todo зачем тебе здесь это поле?
    private int timeslotId;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTimeslotId() {
        return timeslotId;
    }

    public void setTimeslotId(int timeslotId) {
        this.timeslotId = timeslotId;
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
                ", timeslotId=" + timeslotId +
                ", halls=" + halls +
                '}';
    }
}
