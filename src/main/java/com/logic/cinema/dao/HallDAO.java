package com.logic.cinema.dao;

import com.logic.cinema.model.Hall;
import com.logic.cinema.model.HallsList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HallDAO extends JpaRepository<Hall,Long>, CrudRepository<Hall,Long>{
    @Modifying
    @Query("UPDATE Hall h SET h.name = :name WHERE h.id = :id")
    void update(@Param("id") Long id,
                @Param("name") HallsList name);

}
