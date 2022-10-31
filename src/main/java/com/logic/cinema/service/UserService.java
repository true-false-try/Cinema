package com.logic.cinema.service;

import com.logic.cinema.exeptions.FindException;
import com.logic.cinema.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUserTickets();
    Optional<User> findByLoginAndPassword(String login, String password) throws FindException;

}
