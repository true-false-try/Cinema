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
import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class HallServiceImplTest {
    @Mock
    private static HallDAO hallDAO;

    @Mock
    private SeatServiceImpl serviceSeat;

    @InjectMocks
    private HallServiceImpl serviceHall;

    @Captor
    private ArgumentCaptor<Hall> captorHall;

    private static final Long id = 1L;
    private static final HallsList name = HallsList.BLUE;

    @Ignore
    @Test
    void shouldGetPositiveScenarioWhenAllOkForMethodSave() throws AddException {
        Optional<Hall> optionalHall = Optional.of(BuilderHall.forSave());

        when(hallDAO.getHallByName(HallsList.WHITE)).thenReturn(Optional.empty());


        JSONObject returnService = serviceHall.save(BuilderHall.forSave());

        verify(hallDAO).save(captorHall.capture());
        Hall value = captorHall.getValue();
        
        assertNull(returnService);
        /*assertEquals(value.getName(), HallsList.RED);
        value.getSeats()
                .forEach(row -> assertEquals(row.getRow(),3));*/

    }

    @Test
    void shouldGetPositiveScenarioWhenAllOkForMethodUpdate() throws UpdateException {
        Optional<Hall> optionalHall = Optional.of(BuilderHall.okHall());

        when(hallDAO.findById(id)).thenReturn(optionalHall);

        Hall returnService = serviceHall.update(BuilderHall.forUpdate());

        verify(hallDAO).save(captorHall.capture());
        Hall value = captorHall.getValue();

        assertThat(returnService).isNotNull();
        assertEquals(value.getName(), HallsList.RED);
        value.getSeats()
                .forEach(row -> assertEquals(row.getRow(),3));

    }

    @Test
    void shouldGetPositiveScenarioWhenAllOkFormMethodFindById() {
        Optional<Hall> optionalHall = Optional.of(BuilderHall.okHall());

        when(hallDAO.findById(id)).thenReturn(optionalHall);

        Hall returnService = serviceHall.findById(id).get();

        assertThat(returnService).isNotNull();

        assertThat(returnService.getId()).isSameAs(id);
        assertThat(returnService.getName()).isSameAs(name);
        assertThat(returnService.getSeats()).isNotNull();
        assertThat(returnService.getSeats().size()).isSameAs(1);
        returnService.getSeats()
                .forEach(value -> assertEquals(1,value.getRow()));
    }

    @Test
    void shouldGetPositiveScenarioWhenAllOkFormMethodFindByName() {
        Optional<Hall> optionalHall = Optional.of(BuilderHall.okHall());

        when(hallDAO.getHallByName(name)).thenReturn(optionalHall);

        Hall returnService = serviceHall.findByName(name).get();

        assertThat(returnService).isNotNull();

        assertThat(returnService.getId()).isSameAs(id);
        assertThat(returnService.getName()).isSameAs(name);
        assertThat(returnService.getSeats()).isNotNull();
        assertThat(returnService.getSeats().size()).isSameAs(1);
        returnService.getSeats().forEach(value ->
                assertEquals(1,value.getRow()));
    }

    @Test
    void shouldGetPositiveScenarioWhenAllOkFormMethodDelete() throws DeleteException {
        Optional<Hall> optional = Optional.of(BuilderHall.okHall());

        when(hallDAO.findById(id)).thenReturn(optional);

        String returnService = serviceHall.delete(id).toString();

        assertEquals(
                returnService,
                JsonResponse.responseMessage(
                        String.format("Hall %s have been deleted",id)).toString());

    }

    static class BuilderHall {

        public static Hall okHall(){
            Set<Seat> seats = new HashSet<>();
            Seat seat = new Seat();
            Hall hall = new Hall();

            seat.setId(1L);
            seat.setRow(1);
            seat.setSeat(1);
            seat.setStatus(StatusSeatsList.AVAILABLE);
            seats.add(seat);

            hall.setId(1L);
            hall.setName(HallsList.BLUE);
            hall.setSeats(seats);


            return hall;
        }

        public static Hall forUpdate() {
            Set<Seat> seats = new HashSet<>();
            Seat seat = new Seat();
            Hall hall = new Hall();

            seat.setId(5L);
            seat.setRow(3);
            seat.setSeat(2);
            seat.setStatus(StatusSeatsList.NOT_AVAILABLE);
            seats.add(seat);

            hall.setId(1L);
            hall.setName(HallsList.RED);
            hall.setSeats(seats);

            return hall;
        }

        public static Hall forSave(){
            Set<Seat> seats = new HashSet<>();
            Seat seat = new Seat();
            Hall hall = new Hall();

            seat.setId(1L);
            seat.setRow(1);
            seat.setSeat(1);
            seat.setStatus(StatusSeatsList.AVAILABLE);
            seats.add(seat);
            hall.setName(null);
            hall.setId(1L);
            hall.setSeats(seats);


            return hall;
        }
    }
}