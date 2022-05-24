package com.logic.cinema.service.impl;

import com.logic.cinema.repository.UserDAO;
import com.logic.cinema.model.User;
import com.logic.cinema.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> findAllUserTickets() {
        return userDAO.findAll();
    }
}
