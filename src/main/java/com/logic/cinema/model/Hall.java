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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "halls")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")    // @JsonIdentityInfo - use for solve the problem with recursion, when we use @OneToMany @ManyToOne
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Hall implements Cloneable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", length = 30, nullable = false)
    private HallsList name;

    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Seat> seats;

    @Override
    public Hall clone() throws CloneNotSupportedException {
        return (Hall) super.clone();
    }
}
