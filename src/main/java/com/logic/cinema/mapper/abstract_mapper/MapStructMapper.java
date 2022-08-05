package com.logic.cinema.mapper.abstract_mapper;

import com.logic.cinema.dto.HallDTO;
import com.logic.cinema.dto.SeatDTO;
import com.logic.cinema.model.Hall;
import com.logic.cinema.model.Seat;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MapStructMapper<O,D>{
    D createDto(O object);
    default List<D> listDto(List<O> listObjects) {
        List<D> listDto = new ArrayList<>();
        listObjects.forEach(value -> listDto.add(createDto(value)));
        return listDto;
    }
    default O isNull(O object) {
        return object == null ? null : object;
    }
}
