package com.logic.cinema.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tariffs")
public class Tariff {

   @Id
   @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "name", length = 128, nullable = false)
   private String name;

   @Column(name = "cost", length = 10, nullable = false)
   private Double cost;

   public Tariff() {

   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Double getCost() {
      return cost;
   }

   public void setCost(Double cost) {
      this.cost = cost;
   }

   @Override
   public String toString() {
      return "Tariff{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", cost=" + cost +
              '}';
   }
}
