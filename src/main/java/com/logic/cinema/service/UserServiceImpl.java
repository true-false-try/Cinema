package com.logic.cinema.service;

import com.logic.cinema.dao.UserDAO;
import com.logic.cinema.model.User;
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
