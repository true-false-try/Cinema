package com.logic.cinema.service;

import com.logic.cinema.dto.HallDTO;
import com.logic.cinema.exeptions.AddException;
import com.logic.cinema.exeptions.DeleteException;
import com.logic.cinema.exeptions.UpdateException;
import com.logic.cinema.model.Hall;
import com.logic.cinema.model.HallsList;
import org.json.JSONObject;
import org.postgresql.util.PSQLException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface HallService {
    HallDTO save(Hall hall) throws AddException;
    HallDTO update(Hall hall) throws UpdateException, PSQLException;
    List<HallDTO> findAllHalls();
    Optional<Hall> findById(Long id) throws NoSuchElementException;
    Optional<Hall> findByName(HallsList name);
    void delete(Long id) throws DeleteException;

    HallDTO dtoFindById(Long id);
    public HallDTO dtoFindByName(HallsList name);

}
