package com.logic.cinema.service;

import com.logic.cinema.exeptions.AddException;
import com.logic.cinema.exeptions.DeleteException;
import com.logic.cinema.exeptions.UpdateException;
import com.logic.cinema.model.Hall;
import com.logic.cinema.model.HallsList;
import org.json.JSONObject;
import org.postgresql.util.PSQLException;

import java.util.List;
import java.util.Optional;

public interface HallService {
    Hall save(Hall hall) throws AddException;
    Hall update(Hall hall) throws UpdateException, PSQLException;
    List<Hall> findAllHalls();
    Optional<Hall> findById(Long id);
    Optional<Hall> findByName(HallsList name);
    JSONObject delete(Long id) throws DeleteException;
}
