package com.logic.cinema.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "seats")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")    // @JsonIdentityInfo - use for solve the problem with recursion, when we use @OneToMany @ManyToOne

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "hall_id")
    @JsonManagedReference
    private Hall hall;

}
