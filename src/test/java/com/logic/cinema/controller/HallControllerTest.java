package com.logic.cinema.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logic.cinema.model.Hall;
import com.logic.cinema.model.HallsList;
import com.logic.cinema.model.Seat;
import com.logic.cinema.model.StatusSeatsList;
import com.logic.cinema.service.HallService;
import com.logic.cinema.util.JsonResponse;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.HashSet;
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
    @MockBean
    private HallService hallService;

    private static Hall hallFirst;
    private static Hall hallForUpdate;

    private static List<Hall> listWithOneHall;
    private static List<Hall> listWithManyHall;

    @BeforeEach
    void initHall() throws CloneNotSupportedException {

        hallFirst = Hall.builder()
                .id(1L)
                .name(HallsList.ORANGE)
                .seats(Set.of(
                        Seat.builder()
                                .id(1L)
                                .row(1)
                                .seat(1)
                                .status(StatusSeatsList.AVAILABLE).build()))
                .build();

        hallForUpdate = hallFirst.clone();
        hallForUpdate.setId(null);
        hallForUpdate.setName(HallsList.YELLOW);

        Hall hallTwice = hallFirst.clone();
        hallTwice.setId(2L);
        hallTwice.setName(HallsList.WHITE);
        hallTwice.setSeats( Set.of(Seat.builder()
                .id(2L)
                .row(3)
                .seat(2)
                .status(StatusSeatsList.AVAILABLE)
                .build()));

        Hall hallThird = hallFirst.clone();
        hallThird.setId(3L);
        hallThird.setName(HallsList.BLACK);
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

        mvc.perform(MockMvcRequestBuilders.get("/api/halls")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value(HallsList.ORANGE.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].seats.[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].seats.[0].row").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].seats.[0].seat").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].seats.[0].status").value(StatusSeatsList.AVAILABLE.toString()));

    }

    @Test
    void shouldGetManyHall() throws Exception {

        given(hallService.findAllHalls()).willReturn(listWithManyHall);

        mvc.perform(MockMvcRequestBuilders.get("/api/halls")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())

                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value(HallsList.ORANGE.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].seats.[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].seats.[0].row").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].seats.[0].seat").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].seats.[0].status").value(StatusSeatsList.AVAILABLE.toString()))

                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].name").value(HallsList.WHITE.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].seats.[0].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].seats.[0].row").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].seats.[0].seat").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].seats.[0].status").value(StatusSeatsList.AVAILABLE.toString()))

                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].id").value(3L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].name").value(HallsList.BLACK.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].seats.[0].id").value(3L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].seats.[0].row").value(6))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].seats.[0].seat").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].seats.[0].status").value(StatusSeatsList.NOT_AVAILABLE.toString()));
    }

    @Test
    void shouldGetCreatedHall() throws Exception {

        given(hallService.save(any(Hall.class))).willReturn(hallFirst);

        mvc.perform(MockMvcRequestBuilders.post("/api/halls")
                        .content(asJsonString(hallFirst))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(HallsList.ORANGE.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.seats.[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.seats.[0].row").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.seats.[0].seat").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.seats.[0].status").value(StatusSeatsList.AVAILABLE.toString()));
    }

    @Test
    void shouldGetUpdateHall() throws Exception {

        given(hallService.update(any(Hall.class))).willReturn(hallForUpdate);

        Optional<Hall> optional = hallService.findById(hallForUpdate.getId());

        mvc.perform(MockMvcRequestBuilders.put("/api/halls")
                        .content(asJsonString(optional))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(HallsList.YELLOW.toString()));

    }

    @Test
    void shouldGetDeleteHall() throws Exception {

        JSONObject okResponse = JsonResponse.responseMessage(String.format("Hall %s have been deleted",1L));

        given(hallService.delete(hallFirst.getId())).willReturn(okResponse);
        System.out.println(okResponse);

        mvc.perform(MockMvcRequestBuilders.delete(
                String.format("/api/halls/%s", hallFirst.getId()))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(String.valueOf(okResponse)));
    }

    private static String asJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}