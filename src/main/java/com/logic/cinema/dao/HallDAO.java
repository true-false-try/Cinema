package com.logic.cinema.dao;

import com.logic.cinema.model.Hall;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface HallDAO  {

    List<Hall> getAllHalls();
}
