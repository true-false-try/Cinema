package com.logic.cinema.model;

<<<<<<< HEAD
=======
import com.fasterxml.jackson.annotation.*;

>>>>>>> origin/testWeb
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "halls")
<<<<<<< HEAD
=======
// @JsonIdentityInfo - use for solve the problem with recursion, when we use @OneToMany @ManyToOne
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
>>>>>>> origin/testWeb
public class Hall {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    private Integer id;
=======
    private Long id;
>>>>>>> origin/testWeb

    @Enumerated(EnumType.STRING)
    @Column(name = "name", length = 30, nullable = false)
    private HallsList name;

<<<<<<< HEAD
    @OneToMany
=======
    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
>>>>>>> origin/testWeb
    private Set<Seat> seats;

    public Hall(){

    }

<<<<<<< HEAD
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
=======
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
>>>>>>> origin/testWeb
        this.id = id;
    }

    public HallsList getName() {
        return name;
    }

    public void setName(HallsList name) {
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
<<<<<<< HEAD
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
=======
                "id=" + id +
                ", name=" + name +
>>>>>>> origin/testWeb
                ", seats=" + seats +
                '}';
    }
}
