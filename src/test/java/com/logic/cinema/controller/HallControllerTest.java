package com.logic.cinema.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logic.cinema.dto.HallDTO;
import com.logic.cinema.mapper.HallMapper;
import com.logic.cinema.model.Hall;
import com.logic.cinema.model.HallsName;
import com.logic.cinema.model.Seat;
import com.logic.cinema.model.StatusSeatsList;
import com.logic.cinema.service.HallService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(HallController.class)
class HallControllerTest {
    @Autowired
    private MockMvc mvc;
    @Spy
    private HallMapper mapper = Mappers.getMapper(HallMapper.class);
    @MockBean
    private HallService hallService;

    private static String URL_CRUD_OPERATIONS = "/api/halls";
    private static HallDTO hallFirst;
    private static HallDTO hallForUpdate;

    private static List<HallDTO> listWithOneHall;
    private static List<HallDTO> listWithManyHall;

    private static Long ID_NOT_NULL = 1L;

    @BeforeEach
    void init() throws CloneNotSupportedException {
        Hall defaultHall = Hall.builder()
                .id(1L)
                .name(HallsName.ORANGE)
                .seats(Set.of(
                        Seat.builder()
                                .id(1L)
                                .row(1)
                                .seat(1)
                                .status(StatusSeatsList.AVAILABLE).build()))
                .build();

        hallFirst = mapper.toHallDTO(defaultHall);

        hallForUpdate = mapper.toHallDTO(defaultHall.clone());
        hallForUpdate.setId(ID_NOT_NULL);
        hallForUpdate.setName(HallsName.YELLOW);

        HallDTO hallTwice = mapper.toHallDTO(defaultHall.clone());
        hallTwice.setId(2L);
        hallTwice.setName(HallsName.WHITE);
        hallTwice.setSeats( Set.of(Seat.builder()
                .id(2L)
                .row(3)
                .seat(2)
                .status(StatusSeatsList.AVAILABLE)
                .build()));

        HallDTO hallThird = mapper.toHallDTO(defaultHall.clone());
        hallThird.setId(3L);
        hallThird.setName(HallsName.BLACK);
        hallThird.setSeats( Set.of(Seat.builder()
                .id(3L)
                .row(6)
                .seat(3)
                .status(StatusSeatsList.NOT_AVAILABLE)
                .build()));

        listWithOneHall = new ArrayList<>(List.of(hallFirst));
        listWithManyHall = new ArrayList<>(List.of(hallFirst, hallTwice, hallThird));
    }

    @Test
    void shouldGetOneHall() throws Exception {

        given(hallService.findAllHalls()).willReturn(listWithOneHall);

        mvc.perform(MockMvcRequestBuilders.get(URL_CRUD_OPERATIONS)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value(HallsName.ORANGE.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].seats.[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].seats.[0].row").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].seats.[0].seat").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].seats.[0].status").value(StatusSeatsList.AVAILABLE.toString()));

    }

    @Test
    void shouldGetManyHall() throws Exception {

        given(hallService.findAllHalls()).willReturn(listWithManyHall);

        mvc.perform(MockMvcRequestBuilders.get(URL_CRUD_OPERATIONS)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())

                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value(HallsName.ORANGE.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].seats.[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].seats.[0].row").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].seats.[0].seat").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].seats.[0].status").value(StatusSeatsList.AVAILABLE.toString()))

                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].name").value(HallsName.WHITE.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].seats.[0].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].seats.[0].row").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].seats.[0].seat").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].seats.[0].status").value(StatusSeatsList.AVAILABLE.toString()))

                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].id").value(3L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].name").value(HallsName.BLACK.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].seats.[0].id").value(3L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].seats.[0].row").value(6))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].seats.[0].seat").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].seats.[0].status").value(StatusSeatsList.NOT_AVAILABLE.toString()));
    }

    @Test
    void shouldGetCreatedHall() throws Exception {

        given(hallService.save(any(Hall.class))).willReturn(hallFirst);

        mvc.perform(MockMvcRequestBuilders.post(URL_CRUD_OPERATIONS)
                        .content(asJsonString(hallFirst))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(HallsName.ORANGE.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.seats.[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.seats.[0].row").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.seats.[0].seat").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.seats.[0].status").value(StatusSeatsList.AVAILABLE.toString()));
    }

    @Test
    void shouldGetUpdateHall() throws Exception {

        given(hallService.update(any(Hall.class))).willReturn(hallForUpdate);

        Optional<Hall> optional = hallService.findById(hallForUpdate.getId());

        mvc.perform(MockMvcRequestBuilders.put(URL_CRUD_OPERATIONS)
                        .content(asJsonString(optional))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(HallsName.YELLOW.toString()));

    }

    @Test
    void shouldGetDeleteHall() throws Exception {
        Optional<Hall> optional = hallService.findById(hallForUpdate.getId());

        mvc.perform(MockMvcRequestBuilders.put(URL_CRUD_OPERATIONS)
                        .content(asJsonString(optional))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private static String asJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}