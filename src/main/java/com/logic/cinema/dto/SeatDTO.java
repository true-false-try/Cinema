package com.logic.cinema.dto;

import com.logic.cinema.model.constant.StatusSeatsList;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Builder
public class SeatDTO {
    private Long id;
    private int row;
    private int seat;
    @Enumerated(EnumType.STRING)
    private StatusSeatsList status;
}
