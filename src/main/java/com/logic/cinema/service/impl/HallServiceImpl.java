package com.logic.cinema.service.impl;

import com.logic.cinema.exeptions.AddException;
import com.logic.cinema.exeptions.DeleteException;
import com.logic.cinema.exeptions.UpdateException;
import com.logic.cinema.repository.HallDAO;
import com.logic.cinema.model.Hall;

import com.logic.cinema.service.HallService;
import org.postgresql.util.PSQLException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class HallServiceImpl implements HallService {

    private final HallDAO hallDAO;

    public HallServiceImpl(HallDAO hallDAO) {
        this.hallDAO = hallDAO;
    }

    @Override
    public Hall save(Hall hall) throws AddException {
        if (hallDAO.findById(hall.getId()).isEmpty()){
            return hallDAO.save(hall);
        }
        else throw new AddException("Hall with this id: %s is already in use", hall.getId());
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
    public String delete(Long id) throws DeleteException {
        if (hallDAO.findById(id).isPresent()) {
            hallDAO.deleteById(id);
        }
        else throw new DeleteException("Hall not found for id: %s, please enter valid id", id);

        return String.format("Hall %s have been deleted",id);
    }

}
