package com.logic.cinema.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.logic.cinema.model.HallsList;
import com.logic.cinema.model.Seat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Set;

@Getter
@Setter
@Builder
public class HallDTO {
    @JsonProperty("Id")
    private Long id;
    @JsonProperty("Name")
    @Enumerated(EnumType.STRING)
    private HallsList name;
    @JsonProperty("Seats")
    private Set<Seat> seats;
}
