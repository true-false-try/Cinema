package com.logic.cinema.model;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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
    private HallsName name;

    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Seat> seats;

    @Override
    public Hall clone() throws CloneNotSupportedException {
        return (Hall) super.clone();
    }
}
