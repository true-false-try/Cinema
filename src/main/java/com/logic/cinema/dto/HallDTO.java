package com.logic.cinema.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.logic.cinema.model.HallsList;
import com.logic.cinema.model.Seat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class HallDTO {
    @JsonProperty("Id")
    private Long id;
    @JsonProperty("Halls")
    private HallsList hallsList;
    @JsonProperty("Seats")
    private Set<Seat> seats;
}
