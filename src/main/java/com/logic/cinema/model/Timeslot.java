package com.logic.cinema.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "timeslots")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")    // @JsonIdentityInfo - use for solve the problem with recursion, when we use @OneToMany @ManyToOne
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Timeslot {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", length = 10, nullable = false)
    private LocalDate date;

    @Column(name = "start_time", length = 10, nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", length = 10, nullable = false)
    private LocalTime endTime;

    @OneToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @OneToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;
}
