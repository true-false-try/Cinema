package com.logic.cinema.service.impl;

import com.logic.cinema.dto.HallDTO;
import com.logic.cinema.exeptions.DeleteException;
import com.logic.cinema.exeptions.UpdateException;
import com.logic.cinema.mapper.HallMapper;
import com.logic.cinema.model.Hall;
import com.logic.cinema.model.HallsName;
import com.logic.cinema.model.Seat;
import com.logic.cinema.model.StatusSeatsList;
import com.logic.cinema.repository.HallDAO;
import com.logic.cinema.service.SeatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HallServiceImplTest {
    @Mock
    private HallDAO hallDAO;
    @Mock
    private SeatService seatService;

    @Spy
    private HallMapper mapper = Mappers.getMapper(HallMapper.class);

    @InjectMocks
    private HallServiceImpl testedEntry;


    private static final Long ID = null;
    private static final HallsName NAME_FOR_HALL = HallsName.WHITE;
    private static final HallsName NAME_FOR_UPDATE_HALL = HallsName.ORANGE;
    private static Hall hallIdNull;
    private static Hall hallWithId;;
    private static Hall hallForUpdate;

    @BeforeEach
    void init() throws CloneNotSupportedException {
        Set<Seat> seatsList = new HashSet<>(Set.of(
                Seat.builder()
                        .id(1L)
                        .row(1)
                        .seat(1)
                        .status(StatusSeatsList.AVAILABLE)
                        .build()));

        hallIdNull = Hall.builder()
                .name(NAME_FOR_HALL)
                .seats(seatsList)
                .build();

        hallWithId = hallIdNull.clone();
        hallWithId.setId(1L);


        hallForUpdate = hallIdNull.clone();
        hallForUpdate.setName(NAME_FOR_UPDATE_HALL);
    }

    @Test
    void shouldGetOkWhenSaveHall() {
        Optional<Hall> optionalHall = Optional.of(hallWithId);
        when(hallDAO.save(any(Hall.class))).thenReturn(hallWithId);
        when(seatService.saveAll(anySet())).thenReturn(hallIdNull.getSeats());
        when(hallDAO.findById(hallWithId.getId())).thenReturn(optionalHall);

        HallDTO result = testedEntry.save(hallIdNull);

        assertThat(result).isNotNull();
        assertNotNull(result.getName());
    }

    @Test
    void shouldGetOkWhenUpdateHall() throws UpdateException {
        Optional<Hall> optionalHall = Optional.of(hallWithId);

        when(hallDAO.save(any(Hall.class))).thenReturn(hallIdNull);
        when(hallDAO.findById(hallIdNull.getId())).thenReturn(optionalHall);
        when(hallDAO.save(hallForUpdate)).thenReturn(hallForUpdate);

        HallDTO result = testedEntry.update(hallForUpdate);

        assertThat(result).isNotNull();
        assertEquals(result.getName(), NAME_FOR_UPDATE_HALL);
        result.getSeats()
                .forEach(row -> assertEquals(row.getRow(),1));
    }

    @Test
    void shouldGetOkWhenFindByIdHall() {
        Optional<Hall> optionalHall = Optional.of(hallIdNull);

        when(hallDAO.findById(ID)).thenReturn(optionalHall);

        HallDTO result = testedEntry.dtoFindById(ID);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(ID);
        assertThat(result.getName()).isEqualTo(NAME_FOR_HALL);
        assertThat(result.getSeats()).isNotNull();
        assertThat(result.getSeats().size()).isEqualTo(1);
        result.getSeats()
                .forEach(value -> assertEquals(1,value.getRow()));
    }

    @Test
    void shouldGetOkWhenFindByNameHall() {
        Optional<Hall> optionalHall = Optional.of(hallIdNull);

        when(hallDAO.getHallByName(NAME_FOR_HALL)).thenReturn(optionalHall);

        HallDTO result = testedEntry.dtoFindByName(NAME_FOR_HALL);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(ID);
        assertThat(result.getName()).isEqualTo(NAME_FOR_HALL);
        assertThat(result.getSeats()).isNotNull();
        assertThat(result.getSeats().size()).isEqualTo(1);
        result.getSeats().forEach(value ->
                assertEquals(1,value.getRow()));
    }

    @Test
    void shouldGetOkWhenDeleteHall() throws DeleteException {
        Optional<Hall> optional = Optional.of(hallWithId);

        when(hallDAO.findById(ID)).thenReturn(optional);

        testedEntry.delete(ID);
    }
}