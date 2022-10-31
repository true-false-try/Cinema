package com.logic.cinema.service.impl;

import com.logic.cinema.exeptions.FindException;
import com.logic.cinema.repository.UserDAO;
import com.logic.cinema.model.User;
import com.logic.cinema.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    @Override
    public List<User> findAllUserTickets() {
        return userDAO.findAll();
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) throws FindException {
        userDAO.findByLoginAndPassword(login, password);
        return userDAO.findByLoginAndPassword(login, password);
    }
}
