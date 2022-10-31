package com.logic.cinema.repository;

import com.logic.cinema.exeptions.FindException;
import com.logic.cinema.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User,Long> {

    Optional<User> findByLoginAndPassword(String login, String password) throws FindException;
}
