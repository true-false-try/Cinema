package com.logic.cinema.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.logic.cinema.model.Hall;
import com.logic.cinema.model.StatusSeatsList;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Builder
public class SeatDTO {
    @JsonProperty("Id")
    private Long id;
    @JsonProperty("Row")
    private int row;
    @JsonProperty("Seat")
    private int seat;
    @JsonProperty("Status")
    @Enumerated(EnumType.STRING)
    private StatusSeatsList status;
}
