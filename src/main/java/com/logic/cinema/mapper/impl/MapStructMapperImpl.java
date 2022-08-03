package com.logic.cinema.mapper.impl;

import com.logic.cinema.dto.HallDTO;
import com.logic.cinema.mapper.MapStructMapper;
import com.logic.cinema.model.Hall;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapStructMapperImpl implements MapStructMapper {

    @Override
    public HallDTO hallDto(Hall hall) {
        if (hall == null) {
            return null;
        }

       return HallDTO.builder()
               .id(hall.getId())
               .hallsList(hall.getName())
               .seats(hall.getSeats())
               .build();
    }

    @Override
    public List<HallDTO> listHallsDto(List<Hall> halls) {

        List<HallDTO> hallsDTOsList = new ArrayList<>();
        halls.forEach(value -> hallsDTOsList.add(hallDto(value)));

        return hallsDTOsList;
    }
}
