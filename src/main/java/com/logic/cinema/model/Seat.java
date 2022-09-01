package com.logic.cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.EnumType;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
@Entity
@Table(name = "seats")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "hall")
@EqualsAndHashCode(exclude = "hall")
public class Seat {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "row", nullable = false, unique = true)
    @Size(min = 1, max = 12)
    private Integer row;

    @Column(name = "seat", nullable = false, unique = true)
    @Size(min = 1, max = 10)
    private Integer seat;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 2, scale = 10, nullable = false)
    private StatusSeatsList status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hall_id", referencedColumnName = "id")
    @JsonIgnore
    private Hall hall;

}