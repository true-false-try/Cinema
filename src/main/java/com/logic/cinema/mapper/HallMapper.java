package com.logic.cinema.mapper;

import com.logic.cinema.dto.HallDTO;
import com.logic.cinema.model.Hall;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface HallMapper {
    HallDTO toHallDTO(Hall hall);
    Optional<HallDTO> toHallDTO(Optional<Hall> byId);
    List<HallDTO> toHallDTO(List<Hall> all);
}
