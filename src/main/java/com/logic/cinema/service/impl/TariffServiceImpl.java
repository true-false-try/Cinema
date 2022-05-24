package com.logic.cinema.service.impl;

import com.logic.cinema.repository.TariffDAO;
import com.logic.cinema.model.Tariff;
import com.logic.cinema.service.TariffService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TariffServiceImpl implements TariffService {

    private final TariffDAO tariffDAO;

    public TariffServiceImpl(TariffDAO tariffDAO) {
        this.tariffDAO = tariffDAO;
    }

    @Override
    public List<Tariff> findAllTariffs() {
        return tariffDAO.findAll();
    }
}
