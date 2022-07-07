package com.logic.cinema.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logic.cinema.model.Hall;
import com.logic.cinema.model.HallsList;
import com.logic.cinema.model.Seat;
import com.logic.cinema.model.StatusSeatsList;
import com.logic.cinema.repository.HallDAO;
import com.logic.cinema.service.HallService;
import com.logic.cinema.util.JsonResponse;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(HallController.class)
class HallControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private HallService hallService;

    @Before
    Hall getHallWithoutId() {
        Set<Seat> seats = new HashSet<>();
        Seat seat = new Seat();
        Hall hall = new Hall();

        seat.setId(1L);
        seat.setRow(1);
        seat.setSeat(1);
        seat.setStatus(StatusSeatsList.AVAILABLE);

        seats.add(seat);

        hall.setName(HallsList.ORANGE);
        hall.setSeats(seats);
        return hall;
    }

    @Before
    Hall getUpdateHall() {
        Hall hall = new Hall();

        hall.setId(1L);
        hall.setName(HallsList.YELLOW);

        return hall;
    }

    @Before
    Hall getHallFirst() {
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
    Hall getHallTwice() {
        Set<Seat> seats = new HashSet<>();
        Seat seat = new Seat();
        Hall hall = new Hall();

        seat.setId(2L);
        seat.setRow(3);
        seat.setSeat(2);
        seat.setStatus(StatusSeatsList.AVAILABLE);

        seats.add(seat);

        hall.setId(2L);
        hall.setName(HallsList.WHITE);
        hall.setSeats(seats);

        return hall;
    }

    @Before
    Hall getHallThird () {
        Set<Seat> seats = new HashSet<>();
        Seat seat = new Seat();
        Hall hall = new Hall();

        seat.setId(3L);
        seat.setRow(6);
        seat.setSeat(3);
        seat.setStatus(StatusSeatsList.NOT_AVAILABLE);

        seats.add(seat);

        hall.setId(3L);
        hall.setName(HallsList.BLACK);
        hall.setSeats(seats);

        return hall;
    }

    @Before
    List<Hall> setHallsListWithOneParam() {
        return new ArrayList<>(List.of(getHallFirst()));
    }

    @Before
    List<Hall> setHallsListWithManyParams() {
        return new ArrayList<>(List.of(getHallFirst(), getHallTwice(), getHallThird()));
    }



    @Test
    void shouldGetOneHall() throws Exception {

        given(hallService.findAllHalls()).willReturn(setHallsListWithOneParam());

        mvc.perform(get("/api/halls")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[0].name").value(HallsList.ORANGE.toString()))
                .andExpect(jsonPath("$.[0].seats.[0].id").value(1L))
                .andExpect(jsonPath("$.[0].seats.[0].row").value(1))
                .andExpect(jsonPath("$.[0].seats.[0].seat").value(1))
                .andExpect(jsonPath("$.[0].seats.[0].status").value(StatusSeatsList.AVAILABLE.toString()));

    }

    @Test
    void shouldGetManyHall() throws Exception {

        given(hallService.findAllHalls()).willReturn(setHallsListWithManyParams());

        mvc.perform(get("/api/halls")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[0].name").value(HallsList.ORANGE.toString()))
                .andExpect(jsonPath("$.[0].seats.[0].id").value(1L))
                .andExpect(jsonPath("$.[0].seats.[0].row").value(1))
                .andExpect(jsonPath("$.[0].seats.[0].seat").value(1))
                .andExpect(jsonPath("$.[0].seats.[0].status").value(StatusSeatsList.AVAILABLE.toString()))

                .andExpect(jsonPath("$.[1].id").value(2L))
                .andExpect(jsonPath("$.[1].name").value(HallsList.WHITE.toString()))
                .andExpect(jsonPath("$.[1].seats.[0].id").value(2L))
                .andExpect(jsonPath("$.[1].seats.[0].row").value(3))
                .andExpect(jsonPath("$.[1].seats.[0].seat").value(2))
                .andExpect(jsonPath("$.[1].seats.[0].status").value(StatusSeatsList.AVAILABLE.toString()))

                .andExpect(jsonPath("$.[2].id").value(3L))
                .andExpect(jsonPath("$.[2].name").value(HallsList.BLACK.toString()))
                .andExpect(jsonPath("$.[2].seats.[0].id").value(3L))
                .andExpect(jsonPath("$.[2].seats.[0].row").value(6))
                .andExpect(jsonPath("$.[2].seats.[0].seat").value(3))
                .andExpect(jsonPath("$.[2].seats.[0].status").value(StatusSeatsList.NOT_AVAILABLE.toString()));


    }

    @Test
    void shouldGetCreatedHall() throws Exception {

        given(hallService.save(any(Hall.class))).willReturn(getHallFirst());

        mvc.perform(post("/api/halls")
                        .content(asJsonString(getHallWithoutId()))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value(HallsList.ORANGE.toString()))
                .andExpect(jsonPath("$.seats.[0].id").value(1L))
                .andExpect(jsonPath("$.seats.[0].row").value(1))
                .andExpect(jsonPath("$.seats.[0].seat").value(1))
                .andExpect(jsonPath("$.seats.[0].status").value(StatusSeatsList.AVAILABLE.toString()));

    }

    @Test
    void shouldGetUpdateHall() throws Exception {

        given(hallService.update(any(Hall.class))).willReturn(getUpdateHall());

        Optional<Hall> optional = hallService.findById(getUpdateHall().getId());

        mvc.perform(put("/api/halls")
                        .content(asJsonString(optional))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value(HallsList.YELLOW.toString()));

    }

    @Test
    void shouldGetDeleteHall() throws Exception {

        JSONObject okResponse = JsonResponse.responseMessage(String.format("Hall %s have been deleted",1L));

        given(hallService.delete(getHallFirst().getId())).willReturn(okResponse);
        System.out.println(okResponse);

        mvc.perform(delete(
                String.format("/api/halls/%s", getHallFirst().getId()))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(String.valueOf(okResponse)));
    }

    private static String asJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}