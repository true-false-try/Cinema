package com.logic.cinema.mapper;

import com.logic.cinema.dto.SeatDTO;
import com.logic.cinema.model.Seat;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SeatMapper {

    SeatDTO toSeatDTO(Seat seat);
    List<SeatDTO> toListSeatsDTO(List<Seat> seats);
    Set<SeatDTO> toListSeatsDTO(Set<Seat> seats);


}
