package com.logic.cinema.model;

import javax.persistence.*;
import java.util.Set;

//@Entity
//@Table(name = "tariffs")
public class Tariff {

   @Id
   @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   @Column(name = "name", length = 128, nullable = false)
   private String name;

   @Column(name = "cost", length = 10, nullable = false)
   private Double cost;

   @OneToMany
   @JoinColumn(name = "movie_id")
   private Set<Movie> movie;

   public Tariff() {

   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
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

   public Set<Movie> getMovie() {
      return movie;
   }

   public void setMovie(Set<Movie> movie) {
      this.movie = movie;
   }

   @Override
   public String toString() {
      return "Tariff{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", cost=" + cost +
              ", movie=" + movie +
              '}';
   }
}
