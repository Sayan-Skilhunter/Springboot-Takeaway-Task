package com.houserent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.houserent.entities.House;

public interface HouseRepository extends JpaRepository<House, Long> {

    @Query("SELECT t FROM House t WHERE t.city = :arg OR t.locality = :arg")
    public List<House> findByCityOrLoc(@Param("arg") String arg);
}
