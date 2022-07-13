package com.logic.cinema.controller;

import com.logic.cinema.model.User;
import com.logic.cinema.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/list")
    List<User> allUsers(){
        return userService.findAllUserTickets();
    }
}
