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

   @ManyToMany(fetch = FetchType.LAZY)
   @JoinTable(name = "tickets",
           joinColumns = {@JoinColumn(name = "tariff_id")},
           inverseJoinColumns = {@JoinColumn(name = "timeslot_id")}
   )
   private List<Ticket> ticketList;


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

   public List<Ticket> getTicketList() {
      return ticketList;
   }

   public void setTicketList(List<Ticket> ticketList) {
      this.ticketList = ticketList;
   }

   @Override
   public String toString() {
      return "Tariff{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", cost=" + cost +
              ", ticketList=" + ticketList +
              '}';
   }
}
