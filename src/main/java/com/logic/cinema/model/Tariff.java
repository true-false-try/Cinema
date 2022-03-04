package com.logic.cinema.model;

import javax.persistence.*;
<<<<<<< HEAD
import java.util.Set;
=======
import java.util.List;
>>>>>>> origin/testWeb

@Entity
@Table(name = "tariffs")
public class Tariff {

   @Id
   @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
   private Integer id;
=======
   private Long id;
>>>>>>> origin/testWeb

   @Column(name = "name", length = 128, nullable = false)
   private String name;

   @Column(name = "cost", length = 10, nullable = false)
   private Double cost;

<<<<<<< HEAD
   @OneToMany
   @JoinColumn(name = "movie_id")
   private Set<Movie> movie;

=======
>>>>>>> origin/testWeb
   public Tariff() {

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

<<<<<<< HEAD
   public Set<Movie> getMovie() {
      return movie;
   }

   public void setMovie(Set<Movie> movie) {
      this.movie = movie;
   }

=======
>>>>>>> origin/testWeb
   @Override
   public String toString() {
      return "Tariff{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", cost=" + cost +
<<<<<<< HEAD
              ", movie=" + movie +
=======
>>>>>>> origin/testWeb
              '}';
   }
}
