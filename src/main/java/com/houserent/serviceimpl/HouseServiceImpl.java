package com.houserent.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.houserent.entities.House;
import com.houserent.repository.HouseRepository;
import com.houserent.service.HouseService;

@Service
public class HouseServiceImpl implements HouseService {
	@Autowired
	private HouseRepository houseRepo;

	@Override
	public List<House> findAllHouse() {
		List<House> houseList = null;
		try {
			houseList = houseRepo.findAll();
			return houseList;
		} catch (Exception e) {
			// log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public Long save(House house) {
		House savedObj = houseRepo.save(house);
		return savedObj.getId();
	}
	
	@Override
	public House getById(Long id) {
		House house = null;
		Optional<House> optHouse=houseRepo.findById(id);
		if(optHouse.isPresent())
			house=optHouse.get();
		return house;
	}

	@Override
	public List<House> findMatchingHouse(String arg) {
		List<House> houseList = null;
		try {
			houseList = houseRepo.findByCityOrLoc(arg);
			return houseList;
		} catch (Exception e) {
			// log.error(e.getMessage());
			throw e;
		}
	}
}
