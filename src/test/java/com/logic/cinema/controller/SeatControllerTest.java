package com.logic.cinema.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logic.cinema.dto.SeatDTO;
import com.logic.cinema.mapper.SeatMapper;
import com.logic.cinema.model.Hall;
import com.logic.cinema.model.HallsName;
import com.logic.cinema.model.Seat;
import com.logic.cinema.model.StatusSeatsList;
import com.logic.cinema.service.SeatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;


@RunWith(SpringRunner.class)
@WebMvcTest(SeatController.class)
class SeatControllerTest {
    @Autowired
    private MockMvc mvc;
    @Spy
    private SeatMapper mapper = Mappers.getMapper(SeatMapper.class);
    @MockBean
    private SeatService seatService;



    private static String URL_CRUD_OPERATIONS = "/api/seats";
    private static Long ID_NOT_NULL = 1L;
    private static List<Seat> listAllSeats;
    private static Set<Seat> setAllSeats;
    private static List<SeatDTO> listSeatDTOS;
    private static Set<SeatDTO> setSeatDTOS;
    private static Hall hall;
    private static Hall hallForUpdate;
    private static SeatDTO seatDTO;
    private static Seat seatOne;
    private static Seat seatForUpdate;


    @BeforeEach
    void init() throws CloneNotSupportedException {

        seatOne = Seat.builder()
                .id(1L)
                .row(1)
                .seat(1)
                .status(StatusSeatsList.AVAILABLE)
                .build();
       Seat seatTwo = Seat.builder()
                .id(2L)
                .row(1)
                .seat(2)
                .status(StatusSeatsList.AVAILABLE)
                .build();
       Seat seatThree = Seat.builder()
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

        setAllSeats = new LinkedHashSet<>();
        setAllSeats.add(seatOne);
        setAllSeats.add(seatTwo);
        setAllSeats.add(seatThree);

        hall = Hall.builder()
                .id(1L)
                .name(HallsName.RED)
                .seats(setAllSeats)
                .build();

        hallForUpdate = Hall.builder()
                .id(1L)
                .name(HallsName.RED)
                .seats(new HashSet<>(List.of(seatForUpdate)))
                .build();

        listAllSeats = new ArrayList<>(List.of(seatOne, seatTwo, seatThree));

        listSeatDTOS = mapper.toListSeatsDTO(listAllSeats);
        seatDTO = mapper.toSeatDTO(seatOne);

        setSeatDTOS = new LinkedHashSet<>(mapper.toListSeatsDTO(setAllSeats));

    }


    @Test
    void shouldGetShowAllSeats() throws Exception {
        given(seatService.dtoFindAllSeats()).willReturn(listSeatDTOS);

        mvc.perform(MockMvcRequestBuilders.get(URL_CRUD_OPERATIONS)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].row").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].seat").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].status").value(StatusSeatsList.AVAILABLE.toString()))

                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].row").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].seat").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].status").value(StatusSeatsList.AVAILABLE.toString()))

                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].id").value(3L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].row").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].seat").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].status").value(StatusSeatsList.AVAILABLE.toString()));
    }

    @Test
    void shouldGetFindSeatById() throws Exception {
        given(seatService.dtoFindById(ID_NOT_NULL)).willReturn(seatDTO);

        mvc.perform(MockMvcRequestBuilders.get(URL_CRUD_OPERATIONS.concat("/1"))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.row").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.seat").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(StatusSeatsList.AVAILABLE.toString()));
    }

    @Test
    void shouldGetFindSeatByHallId() throws Exception {
        given(seatService.dtoFindSeatsByHallId(ID_NOT_NULL)).willReturn(Collections.singleton(seatDTO));

        mvc.perform(MockMvcRequestBuilders.get(URL_CRUD_OPERATIONS.concat("/1/hall"))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].row").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].seat").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].status").value(StatusSeatsList.AVAILABLE.toString()));
    }

    @Test
    void shouldGetAddSeats() throws Exception {
        given(seatService.save(any(Hall.class))).willReturn(setSeatDTOS);


        mvc.perform(MockMvcRequestBuilders.post(URL_CRUD_OPERATIONS)
                        .content(asJsonString(hall))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].row").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].seat").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].status").value(StatusSeatsList.AVAILABLE.toString()))

                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].row").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].seat").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].status").value(StatusSeatsList.AVAILABLE.toString()))

                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].id").value(3L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].row").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].seat").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].status").value(StatusSeatsList.AVAILABLE.toString()));

    }

    @Test
    void shouldGetUpdateSeat() throws Exception {
        given(seatService.update(hallForUpdate,ID_NOT_NULL)).willReturn(seatDTO);

        mvc.perform(MockMvcRequestBuilders.put(URL_CRUD_OPERATIONS.concat("/1"))
                        .content(asJsonString(hallForUpdate))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].row").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].seat").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].status").value(StatusSeatsList.AVAILABLE.toString()));
    }

    @Test
    void shouldGetDeleteSeat() throws Exception{
        doNothing().when(seatService).delete(ID_NOT_NULL);

        mvc.perform(MockMvcRequestBuilders.delete(URL_CRUD_OPERATIONS.concat("/1"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        System.out.println(hall.getSeats());


    }

    private static String asJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}