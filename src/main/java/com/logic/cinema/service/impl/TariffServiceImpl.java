package com.logic.cinema.service.impl;

import com.logic.cinema.model.Tariff;
import com.logic.cinema.repository.TariffDAO;
import com.logic.cinema.service.TariffService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TariffServiceImpl implements TariffService {
    private final TariffDAO tariffDAO;

    @Override
    public List<Tariff> findAllTariffs() {
        return tariffDAO.findAll();
    }

    @Override
    public Tariff save(Tariff tariff) {
         return tariffDAO.save(tariff);
    }

}
