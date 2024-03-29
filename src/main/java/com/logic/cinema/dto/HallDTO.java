package com.logic.cinema.dto;

import com.logic.cinema.model.HallsName;
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
    private Long id;
    @Enumerated(EnumType.STRING)
    private HallsName name;
    private Set<Seat> seats;
}
