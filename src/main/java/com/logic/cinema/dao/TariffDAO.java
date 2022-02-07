package com.logic.cinema.dao;

import com.logic.cinema.model.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TariffDAO extends JpaRepository<Tariff,Long> {
}
