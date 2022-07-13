package com.logic.cinema.service.impl;

import com.logic.cinema.exeptions.DeleteException;
import com.logic.cinema.exeptions.UpdateException;
import com.logic.cinema.model.Hall;
import com.logic.cinema.model.HallsList;
import com.logic.cinema.model.Seat;
import com.logic.cinema.model.StatusSeatsList;
import com.logic.cinema.repository.HallDAO;
import com.logic.cinema.util.JsonResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HallServiceImplTest {
    @Mock
    private HallDAO hallDAO;
    @InjectMocks
    private HallServiceImpl testedEntry;

    private static final Long ID = null;
    private static final HallsList NAME_FOR_HALL = HallsList.WHITE;
    private static final HallsList NAME_FOR_UPDATE_HALL = HallsList.ORANGE;
    private static Hall hall;
    private static Hall hallForUpdate;

    @BeforeEach
    void initHall() throws CloneNotSupportedException {
        Set<Seat> seatsList = new HashSet<>(Set.of(
                Seat.builder()
                        .id(1L)
                        .row(1)
                        .seat(1)
                        .status(StatusSeatsList.AVAILABLE)
                        .build()));

        hall = Hall.builder()
                .name(NAME_FOR_HALL)
                .seats(seatsList)
                .build();

        hallForUpdate = hall.clone();
        hallForUpdate.setName(NAME_FOR_UPDATE_HALL);
    }

    @Test
    void shouldGetOkWhenSaveHall() {
        when(hallDAO.save(any(Hall.class))).thenReturn(hall);

        Hall result = testedEntry.save(hall);

        assertThat(result).isNotNull();
        assertNotNull(result.getName());
    }

    @Test
    void shouldGetOkWhenUpdateHall() throws UpdateException {
        Optional<Hall> optionalHall = Optional.of(hall);

        when(hallDAO.save(any(Hall.class))).thenReturn(hall);
        when(hallDAO.findById(hall.getId())).thenReturn(optionalHall);
        when(hallDAO.save(hallForUpdate)).thenReturn(hallForUpdate);

        Hall result = testedEntry.update(hallForUpdate);

        assertThat(result).isNotNull();
        assertEquals(result.getName(), NAME_FOR_UPDATE_HALL);
        result.getSeats()
                .forEach(row -> assertEquals(row.getRow(),1));
    }

    @Test
    void shouldGetOkWhenFindByIdHall() {
        Optional<Hall> optionalHall = Optional.of(hall);

        when(hallDAO.findById(ID)).thenReturn(optionalHall);

        Hall result = testedEntry.findById(ID).get();

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
        Optional<Hall> optionalHall = Optional.of(hall);

        when(hallDAO.getHallByName(NAME_FOR_HALL)).thenReturn(optionalHall);

        Hall result = testedEntry.findByName(NAME_FOR_HALL).get();

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
        Optional<Hall> optional = Optional.of(hall);

        when(hallDAO.findById(ID)).thenReturn(optional);

        String result = testedEntry.delete(ID).toString();

        assertEquals(
                result,
                JsonResponse.responseMessage(
                        String.format("Hall %s have been deleted",ID)).toString());
    }
}