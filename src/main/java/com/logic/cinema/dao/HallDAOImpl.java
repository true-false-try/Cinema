package com.logic.cinema.dao;

import com.logic.cinema.model.Hall;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
// зачем ты операции типа get делаешь транзакционными? обычно делают операции типа save, update транзакционными
public class HallDAOImpl implements HallDAO{

    @Override
    public List<Hall> getAllHalls() {
        return null;
        // null!!!!!!!!!!!
    }

}
