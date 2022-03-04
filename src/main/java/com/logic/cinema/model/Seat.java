package com.logic.cinema.model;

<<<<<<< HEAD
=======
import com.fasterxml.jackson.annotation.*;

>>>>>>> origin/testWeb
import javax.persistence.*;

@Entity
@Table(name = "seats")
<<<<<<< HEAD
=======
// @JsonIdentityInfo - use for solve the problem with recursion, when we use @OneToMany @ManyToOne
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
>>>>>>> origin/testWeb
public class Seat {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    private int id;
=======
    private Long id;
>>>>>>> origin/testWeb

    @Column(name = "row", length = 3, nullable = false)
    private int row;

    @Column(name = "seat", length = 3, nullable = false)
    private int seat;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 2, nullable = false)
    private StatusSeatsList status;

<<<<<<< HEAD
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hall_id")
    private Hall halls;
=======
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "hall_id")
    private Hall hall;

>>>>>>> origin/testWeb

    public Seat() {

    }

<<<<<<< HEAD
    public int getId() {
        return id;
    }

    public void setId(int id) {
=======
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
>>>>>>> origin/testWeb
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
<<<<<<< HEAD
        return halls;
    }

    public void setHalls(Hall halls) {
        this.halls = halls;
=======
        return hall;
    }

    public void setHalls(Hall halls) {
        this.hall = halls;
>>>>>>> origin/testWeb
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", row=" + row +
                ", seat=" + seat +
                ", status=" + status +
<<<<<<< HEAD
                ", halls=" + halls +
=======
                ", halls=" + hall +
>>>>>>> origin/testWeb
                '}';
    }
}
