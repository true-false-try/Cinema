package com.logic.cinema.service;

import com.logic.cinema.model.Hall;
import java.util.List;

public interface HallService {

    String statusHall(String hallName);

    List<Hall> findAllHalls();
}
