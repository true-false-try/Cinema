package com.logic.cinema.mapper.impl;

import com.logic.cinema.dto.HallDTO;
import com.logic.cinema.mapper.HallMapper;
import com.logic.cinema.model.Hall;
import org.springframework.stereotype.Component;

@Component
public class HallMapperImpl implements HallMapper {

    @Override
    public HallDTO createDto(Hall hall) {
        isNull(hall);
        return HallDTO.builder()
                .id(hall.getId())
                .hallsList(hall.getName())
                .seats(hall.getSeats())
                .build();
    }
}
