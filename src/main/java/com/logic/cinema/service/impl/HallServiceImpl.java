package com.logic.cinema.service.impl;

import com.logic.cinema.exeptions.AddException;
import com.logic.cinema.exeptions.DeleteException;
import com.logic.cinema.exeptions.UpdateException;
import com.logic.cinema.model.HallsList;
import com.logic.cinema.model.Seat;
import com.logic.cinema.repository.HallDAO;
import com.logic.cinema.model.Hall;

import com.logic.cinema.repository.SeatDAO;
import com.logic.cinema.service.HallService;
import com.logic.cinema.service.SeatService;
import com.logic.cinema.util.JsonResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class HallServiceImpl implements HallService {

    private final HallDAO hallDAO;
    private final SeatServiceImpl seatService;



    public HallServiceImpl(HallDAO hallDAO, SeatServiceImpl seatService) {
        this.hallDAO = hallDAO;
        this.seatService = seatService;
    }

    @Override
    public JSONObject save(Hall hall) throws AddException {
        Hall saveHall = new Hall(hall.getName());
        Set<Seat> seatsList = new HashSet<>(hall.getSeats());

        if (findByName(hall.getName()).isEmpty()) {

            hallDAO.save(saveHall);

            seatsList.forEach(seat -> seat.setHalls(saveHall));
            seatsList.forEach(seatService::save);

            return JsonResponse.responseMessage("Hall was added");

        }
        else throw new AddException("Hall with this name: %s is already in use", hall.toName());
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
    public List<Hall> findAllHalls() {
        return hallDAO.findAll();
    }

    @Override
    public Optional<Hall> findById(Long id) {
        return hallDAO.findById(id);
    }

    @Override
    public Optional<Hall> findByName(HallsList name) {
        return hallDAO.getHallByName(name);
    }

    @Override
    public JSONObject delete(Long id) throws DeleteException {
        if (hallDAO.findById(id).isPresent()) {
            hallDAO.deleteById(id);
        }
        else throw new DeleteException("Hall not found for id: %s, please enter valid id", id);

        return JsonResponse.responseMessage(String.format("Hall %s have been deleted",id));
    }

}
