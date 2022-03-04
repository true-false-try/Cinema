package com.logic.cinema.controller;

import com.logic.cinema.model.Tariff;
import com.logic.cinema.service.TariffServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("tariff")
public class TariffController {

    private final TariffServiceImpl tariffService;

    public TariffController(TariffServiceImpl tariffService) {
        this.tariffService = tariffService;
    }

    @GetMapping("/list")
    public List<Tariff> allTarrifs(){
        return tariffService.findAllTariffs();
    }
}
