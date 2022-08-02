package com.logic.cinema.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tariffs")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tariff {
   @Id
   @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "name", length = 128, nullable = false)
   private String name;

   @Column(name = "cost", length = 10, nullable = false)
   private Double cost;

}
