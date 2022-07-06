package com.logic.cinema.service.impl;

import com.logic.cinema.exeptions.AddException;
import com.logic.cinema.exeptions.DeleteException;
import com.logic.cinema.exeptions.UpdateException;
import com.logic.cinema.model.Hall;
import com.logic.cinema.model.HallsList;
import com.logic.cinema.model.Seat;
import com.logic.cinema.model.StatusSeatsList;
import com.logic.cinema.repository.HallDAO;
import com.logic.cinema.util.JsonResponse;
import org.junit.Before;
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
    private static HallDAO hallDAO;

    @InjectMocks
    private HallServiceImpl testedEntry;

    private static final Long id = 1L;
    private static final HallsList NAME = HallsList.ORANGE;


    @Before
    Hall setHall() {
        Set<Seat> seats = new HashSet<>();
        Seat seat = new Seat();
        Hall hall = new Hall();

        seat.setId(1L);
        seat.setRow(1);
        seat.setSeat(1);

        seat.setStatus(StatusSeatsList.AVAILABLE);
        seats.add(seat);
        hall.setId(1L);
        hall.setName(HallsList.ORANGE);
        hall.setSeats(seats);

        return hall;
    }

    @Before
    Hall updateHall() {
        Set<Seat> seats = new HashSet<>();
        Seat seat = new Seat();
        Hall hall = new Hall();

        seat.setId(1L);
        seat.setRow(2);
        seat.setSeat(3);

        seat.setStatus(StatusSeatsList.NOT_AVAILABLE);
        seats.add(seat);
        hall.setId(1L);
        hall.setName(HallsList.WHITE);
        hall.setSeats(seats);

        return hall;
    }

    @Test
    void shouldGetOkWhenSaveHall() throws AddException {

        when(hallDAO.save(any(Hall.class))).thenReturn(setHall());
        Hall resultService = testedEntry.save(setHall());

        assertThat(resultService).isNotNull();

        assertNotNull(resultService.getName());

    }

    @Test
    void shouldGetOkWhenUpdateHall() throws UpdateException {

        Optional<Hall> optionalHall = Optional.of(setHall());

        when(hallDAO.save(any(Hall.class))).thenReturn(setHall());
        when(hallDAO.findById(setHall().getId())).thenReturn(optionalHall);

        Hall resultService = testedEntry.update(updateHall());

        assertThat(resultService).isNotNull();
        assertEquals(resultService.getName(), NAME);
        resultService.getSeats()
                .forEach(row -> assertEquals(row.getRow(),1));

    }

    @Test

    void shouldGetOkWhenFindByIdHall() {
        Optional<Hall> optionalHall = Optional.of(setHall());

        when(hallDAO.findById(id)).thenReturn(optionalHall);

        Hall resultService = testedEntry.findById(id).get();

        assertThat(resultService).isNotNull();

        assertThat(resultService.getId()).isEqualTo(id);
        assertThat(resultService.getName()).isEqualTo(NAME);
        assertThat(resultService.getSeats()).isNotNull();
        assertThat(resultService.getSeats().size()).isEqualTo(1);
        resultService.getSeats()
                .forEach(value -> assertEquals(1,value.getRow()));
    }

    @Test

    void shouldGetOkWhenFindByNameHall() {
        Optional<Hall> optionalHall = Optional.of(setHall());

        when(hallDAO.getHallByName(NAME)).thenReturn(optionalHall);

        Hall resultService = testedEntry.findByName(NAME).get();

        assertThat(resultService).isNotNull();

        assertThat(resultService.getId()).isEqualTo(id);
        assertThat(resultService.getName()).isEqualTo(NAME);
        assertThat(resultService.getSeats()).isNotNull();
        assertThat(resultService.getSeats().size()).isEqualTo(1);
        resultService.getSeats().forEach(value ->
                assertEquals(1,value.getRow()));
    }

    @Test
    void shouldGetOkWhenDeleteHall() throws DeleteException {
        Optional<Hall> optional = Optional.of(setHall());

        when(hallDAO.findById(id)).thenReturn(optional);

        String resultService = testedEntry.delete(id).toString();

        assertEquals(
                resultService,
                JsonResponse.responseMessage(
                        String.format("Hall %s have been deleted",id)).toString());

    }
}