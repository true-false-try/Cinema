package com.logic.cinema.service.impl;

import com.logic.cinema.exeptions.DeleteException;
import com.logic.cinema.exeptions.UpdateException;
import com.logic.cinema.model.Hall;
import com.logic.cinema.model.HallsList;
import com.logic.cinema.model.Seat;
import com.logic.cinema.repository.HallDAO;
import com.logic.cinema.service.HallService;
import com.logic.cinema.service.SeatService;
import com.logic.cinema.util.JsonResponse;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class HallServiceImpl implements HallService {
    private final HallDAO hallDAO;
    private final SeatService seatService;

    @Override
    @Transactional
    public Hall save(Hall hall) {
        Set<Seat> seats = hall.getSeats();
        hall.setId(null);
        hall.setSeats(null);
        Hall storedHall = hallDAO.save(hall);

        seats.forEach(seat -> seat.setHall(storedHall));
        seats = seatService.saveAll(seats);
        storedHall.setSeats(seats);

        return createResponseForSave(storedHall);
    }

    @Override
    @Transactional
    public Hall update(Hall hall) throws UpdateException {
        Optional<Hall> hallFind = findById(hall.getId());
        if (hallFind.isPresent()) {
            return hallDAO.save(hall);
        } else throw new UpdateException("Wasn't find id number, maybe this id is wrong");
    }

    @Override
    @Transactional(readOnly = true)
    public List<Hall> findAllHalls() {
        return hallDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Hall> findById(Long id) throws NoSuchElementException {
        return hallDAO.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Hall> findByName(HallsList name) {
        return hallDAO.getHallByName(name);
    }

    @Override
    @Transactional
    public JSONObject delete(Long id) throws DeleteException {
        if (hallDAO.findById(id).isPresent()) {
            hallDAO.deleteById(id);
        }
        else throw new DeleteException("Hall not found for id: %s, please enter valid id", id);

        return JsonResponse.responseMessage(String.format("Hall %s have been deleted",id));
    }

    private Hall createResponseForSave(Hall hall) {
        Optional<Hall> optionalHall = findById(hall.getId());
        return optionalHall.get();
    }
}
