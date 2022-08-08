package com.logic.cinema.mapper;

import com.logic.cinema.dto.HallDTO;
import com.logic.cinema.model.Hall;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
    HallDTO toHallDTO(Hall hall);
    List<HallDTO> toListHallsDTO(List<Hall> hall);


}
