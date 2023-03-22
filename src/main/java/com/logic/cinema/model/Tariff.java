package com.logic.cinema.model;

import com.logic.cinema.model.constant.TariffsType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

   @Enumerated(EnumType.STRING)
   @Column(name = "type", length = 128, nullable = false)
   private TariffsType type;

   @Column(name = "cost", length = 10, nullable = false)
   private Double cost;

}
