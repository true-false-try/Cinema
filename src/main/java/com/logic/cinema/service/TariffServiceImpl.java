package com.logic.cinema.service;

import com.logic.cinema.dao.TariffDAO;
import com.logic.cinema.model.Tariff;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TariffServiceImpl implements TariffService{

    private final TariffDAO tariffDAO;

    public TariffServiceImpl(TariffDAO tariffDAO) {
        this.tariffDAO = tariffDAO;
    }

    @Override
    public List<Tariff> findAllTariffs() {
        return tariffDAO.findAll();
    }
}
