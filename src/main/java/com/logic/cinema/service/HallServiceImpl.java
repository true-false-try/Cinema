package com.logic.cinema.service;

import com.logic.cinema.dao.HallDAO;
import com.logic.cinema.model.Hall;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HallServiceImpl implements HallService{

    private final HallDAO hallDAO;

    public HallServiceImpl(HallDAO hallDAO) {
        this.hallDAO = hallDAO;
    }

    @Override
    public String statusHall(String hallName) {
        return null;
    }

    @Override
    public List<Hall> findAllHalls() {
        return hallDAO.findAll();
    }

}
