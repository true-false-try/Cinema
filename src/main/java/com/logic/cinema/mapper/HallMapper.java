package com.logic.cinema.mapper;

import com.logic.cinema.dto.HallDTO;
import com.logic.cinema.model.Hall;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring",injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface HallMapper {
    HallDTO toHallDTO(Hall hall);
    List<HallDTO> toHallDTO(List<Hall> all);
}
