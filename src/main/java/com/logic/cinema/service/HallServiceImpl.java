package com.logic.cinema.service;

import com.logic.cinema.dao.HallDAO;
import com.logic.cinema.model.Hall;

import com.logic.cinema.model.HallsList;
import com.logic.cinema.model.Seat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class HallServiceImpl implements HallService{

    private final HallDAO hallDAO;

    public HallServiceImpl(HallDAO hallDAO) {
        this.hallDAO = hallDAO;
    }

    @Override
    public String save(Hall hall) {
        if (findById(hall.getId()).isEmpty()) {
            hallDAO.save(hall);
        } else
            return "Hall wasn't saved because this Hall already exists";

        return String.format("Hall {id: %d, name: %s} was add",hall.getId(),hall.getName());
    }

    @Override
    @Transactional
    public String update(Hall hall){
        if (findById(hall.getId()).isPresent())
            hallDAO.update(
                    findById(hall.getId()).get().getId(),
                    hall.getName());

        else
            return "Hall not found, please try again";
        return "Hall was update";
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
    public String delete(Long id) {
        if (hallDAO.findById(id).isPresent())
            hallDAO.deleteById(id);
        else
            return String.format("Hall not found for id: %s, please enter valid id",id);

        return String.format("Hall %s have been deleted",id);
    }

}
