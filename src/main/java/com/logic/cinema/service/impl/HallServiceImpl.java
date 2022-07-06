package com.logic.cinema.service.impl;

import com.logic.cinema.exeptions.DeleteException;
import com.logic.cinema.exeptions.UpdateException;
import com.logic.cinema.model.Hall;
import com.logic.cinema.model.HallsList;
import com.logic.cinema.repository.HallDAO;
import com.logic.cinema.service.HallService;
import com.logic.cinema.util.JsonResponse;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class HallServiceImpl implements HallService {

    private final HallDAO hallDAO;
    private final SeatServiceImpl seatService;

    public HallServiceImpl(HallDAO hallDAO, SeatServiceImpl seatService) {
        this.hallDAO = hallDAO;
        this.seatService = seatService;
    }

    @Override
    public Hall save(Hall hall) {
        hall.setId(null);

        hall.getSeats().forEach(entry -> entry.setHalls(hall));
        return hallDAO.save(hall);
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
    //Read only
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
