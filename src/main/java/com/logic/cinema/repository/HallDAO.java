package com.logic.cinema.repository;

import com.logic.cinema.model.Hall;
import com.logic.cinema.model.HallsName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HallDAO extends JpaRepository<Hall,Long>, CrudRepository<Hall,Long>{
    Optional<Hall> getHallByName(HallsName name);

}
