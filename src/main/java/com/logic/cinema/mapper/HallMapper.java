package com.logic.cinema.mapper;

import com.logic.cinema.dto.HallDTO;
import com.logic.cinema.mapper.abstract_mapper.MapStructMapper;
import com.logic.cinema.model.Hall;

public interface HallMapper extends MapStructMapper<Hall, HallDTO> {
    HallDTO createDto(Hall hall);
}
