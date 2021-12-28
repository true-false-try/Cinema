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
public class HallController {

    @Autowired
    private HallService hallService;

    @RequestMapping(value = "/halls", method = RequestMethod.GET)
    public String viewAllHalls(Model model){
        model.addAttribute("listHalls", hallService.showAvalibleSeat());
        return "hall/allHals";
    }
}
