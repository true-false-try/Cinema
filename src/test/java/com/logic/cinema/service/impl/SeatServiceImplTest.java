package com.logic.cinema.service.impl;

import com.logic.cinema.dto.SeatDTO;
import com.logic.cinema.exeptions.DeleteException;
import com.logic.cinema.exeptions.UpdateException;
import com.logic.cinema.mapper.SeatMapper;
import com.logic.cinema.model.Hall;
import com.logic.cinema.model.HallsList;
import com.logic.cinema.model.Seat;
import com.logic.cinema.model.StatusSeatsList;
import com.logic.cinema.repository.SeatDAO;
import com.logic.cinema.service.HallService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SeatServiceImplTest {
    @Mock
    private SeatDAO seatDAO;
    @Mock
    private HallService hallService;
    @Spy
    private SeatMapper mapper = Mappers.getMapper(SeatMapper.class);
    @InjectMocks
    private SeatServiceImpl testEntry;

    private static Long ID_NOT_NULL = 1L;
    private static List<Seat> allSeats;
    private static Hall hall;
    private static Hall hallForUpdate;
    private static Seat seatOne;
    private static Seat seatTwo;
    private static Seat seatThree;
    private static Seat seatForUpdate;

    @BeforeEach
    void init() {
        seatOne = Seat.builder()
                .id(1L)
                .row(1)
                .seat(1)
                .status(StatusSeatsList.AVAILABLE)
                .build();
        seatTwo = Seat.builder()
                .id(2L)
                .row(1)
                .seat(2)
                .status(StatusSeatsList.AVAILABLE)
                .build();
        seatThree = Seat.builder()
                .id(3L)
                .row(1)
                .seat(3)
                .status(StatusSeatsList.AVAILABLE)
                .build();

        seatForUpdate = Seat.builder()
                .row(10)
                .seat(10)
                .status(StatusSeatsList.NOT_AVAILABLE)
                .build();

        hall = Hall.builder()
                .id(1L)
                .name(HallsList.RED)
                .seats(new HashSet<>(List.of(seatOne, seatTwo, seatThree)))
                .build();

        hallForUpdate = Hall.builder()
                .id(1L)
                .name(HallsList.RED)
                .seats(new HashSet<>(List.of(seatForUpdate)))
                .build();

        allSeats = new ArrayList<>(List.of(seatOne, seatTwo, seatThree));
    }
    @Test
    void shouldGetOkWhenFindAllSeats() {
        when(seatDAO.findAll()).thenReturn(allSeats);

        List<SeatDTO> result = testEntry.dtoFindAllSeats();

        assertEquals(result.size(), 3);
        assertThat(result.get(0).getId()).isEqualTo(1);
        assertThat(result.get(1).getSeat()).isEqualTo(2);
        assertThat(result.get(2).getStatus()).isEqualTo(StatusSeatsList.AVAILABLE);

    }

    @Test
    void shouldGetOkWhenFindById() {
        Optional<Seat> optionalSeat = Optional.of(seatOne);
        when(seatDAO.findById(any())).thenReturn(optionalSeat);

        SeatDTO result = testEntry.dtoFindById(ID_NOT_NULL);

        assertThat(result).isNotNull();
        assertEquals(result.getId(), 1L);
        assertEquals(result.getSeat(), 1);
        assertEquals(result.getRow(), 1);
        assertEquals(result.getStatus(), StatusSeatsList.AVAILABLE);

    }

    @Test
    void shouldGetOkWhenFindSeatsByHallId() {

        when(seatDAO.findSeatsByHallId(any())).thenReturn(hall.getSeats());

        Set<SeatDTO> result = testEntry.dtoFindSeatsByHallId(hall.getId());

        assertEquals(result.size(), 3);

    }

    @Test
    void shouldGetOkWhenSave() throws UpdateException {
        Optional<Hall> optionalHall = Optional.of(hall);
        when(hallService.findById(any())).thenReturn(optionalHall);
        when(seatDAO.saveAll(any())).thenReturn(allSeats);

        Set<SeatDTO> result = testEntry.save(hall);

        assertEquals(result.size(), 3);

    }

    @Test
    void shouldGetOkWhenSaveAll() {
        when(seatDAO.saveAll(any())).thenReturn(allSeats);

        Set<Seat> result = testEntry.saveAll(hall.getSeats());

        assertEquals(result.size(), 3);

    }

    @Test
    void shouldGetOkWhenUpdate() throws UpdateException {
        Optional<Seat> optionalSeat = hall.getSeats().stream()
                             .filter(v -> v.getId().equals(ID_NOT_NULL))
                             .findFirst();
        when(seatDAO.findSeatByIdAndHallId(hall.getId(), ID_NOT_NULL)).thenReturn(optionalSeat);
        when(seatDAO.save(seatForUpdate)).thenReturn(seatForUpdate);

        SeatDTO result = testEntry.update(hallForUpdate, ID_NOT_NULL);

        assertNotNull(result);
        assertEquals(result.getId(), 1L);
        assertEquals(result.getSeat(), 10);
        assertEquals(result.getRow(), 10);
        assertEquals(result.getStatus(), StatusSeatsList.NOT_AVAILABLE);

    }

    @Test
    void delete() throws DeleteException {
        when(seatDAO.findById(any())).thenReturn(allSeats.stream().filter(v -> v.getId().equals(ID_NOT_NULL)).findFirst());
        testEntry.delete(ID_NOT_NULL);

    }

}