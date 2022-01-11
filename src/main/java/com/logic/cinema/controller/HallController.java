package com.logic.cinema.controller;

import com.logic.cinema.model.HallsList;
import com.logic.cinema.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("cinema")
@Controller
// ты пишешь API. используй RestController
public class HallController {

    @Autowired
    // данную аннотацию использовать не в тестовом коде - плохой тон
    // почитай https://habr.com/ru/post/334636/
    private HallService hallService;

    @RequestMapping(value = "/halls", method = RequestMethod.GET)
    // почитай https://habr.com/ru/post/351890/
    public String viewAllHalls(Model model){
        // переделай так, чтоб не использовать Model. в том примере что я дал - так и есть
        model.addAttribute("listHalls", hallService.showAvalibleSeat());
        return "hall/allHals";
        // слишком длинные пути.
    }
}
