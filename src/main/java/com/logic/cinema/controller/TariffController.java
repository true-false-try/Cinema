package com.logic.cinema.controller;

import com.logic.cinema.model.Tariff;
import com.logic.cinema.service.impl.TariffServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("tariff")
@AllArgsConstructor
public class TariffController {

    private final TariffServiceImpl tariffService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('all:read')")
    public List<Tariff> allTarrifs(){
        return tariffService.findAllTariffs();
    }
}
