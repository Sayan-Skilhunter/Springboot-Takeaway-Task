package com.houserent.service;

import java.util.List;

import com.houserent.entities.House;

public interface HouseService {

	public List<House> findAllHouse();
	
	public Long save(House house);
	
	public House getById(Long id);

	public List<House> findMatchingHouse(String arg);
}
