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
    public Hall save(Hall hall) {
        return hallDAO.save(hall);
    }

    @Override
    @Transactional
    public void update(Long id, HallsList name, Set<Seat> seats) {
        if (hallDAO.findById(id).isPresent()){
            hallDAO.update(
                    id,
                    name,
                    seats
            );
        } else {
            Exception exception = new NullPointerException();
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public List<Hall> findAllHalls() {
        return hallDAO.findAll();
    }

    @Override
    public Hall findById(Long id) {
        Optional<Hall> optionalHall = hallDAO.findById(id);
        Hall hall;
        if (optionalHall.isPresent()) {
            hall = optionalHall.get();
        } else {
            throw new RuntimeException("Hall not found for id :  " + id);
        }
        return hall;
    }

    @Override
    public void delete(Long id) {
        if (hallDAO.findById(id).isPresent()){
            hallDAO.deleteById(id);
        }
        else {
            throw new RuntimeException("Hall not found for id, please enter valid id");
        }
    }

}
