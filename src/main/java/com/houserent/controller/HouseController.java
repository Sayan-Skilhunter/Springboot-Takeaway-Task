package com.houserent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.houserent.entities.House;
import com.houserent.service.HouseService;

@Controller
public class HouseController {
	@Autowired
	private HouseService houseService;

	@GetMapping(value = { "index", "/" })
	public String index(Model model) {
		List<House> houseList = null;
		try {
			houseList = houseService.findAllHouse();
			model.addAttribute("houseList", houseList);
		} catch (Exception e) {
			// log.error("Error occurred while fetching object");
			// throw new CustomException;
			throw e;
		}
		return "index";
	}

	@GetMapping(value = "save")
	public String save(Model model) {
		model.addAttribute("house", new House());
		return "save";
	}

	@PostMapping(value = "save")
	public String save(@ModelAttribute House house) {
		String resultPage = null;
		try {
			house.setStatus("Available");
			Long id = houseService.save(house);
			if (id > 0)
				resultPage = "/";
		} catch (Exception e) {
			// log.error("Error occurred while saving object");
			// throw new CustomException;
			throw e;
		}
		return "redirect:" + resultPage;
	}

	@ResponseBody
	@PostMapping(value = "rent")
	public String rent(@RequestParam(name = "id") Long id) {
		House house = houseService.getById(id);
		if (house != null) {
			house.setStatus("Rented");
			houseService.save(house);
			return "Success";
		} else {
			return "Invalid Id";
		}

	}

	@GetMapping(value = "search/{action}")
	public String search(Model model, @PathVariable("action") String action) {
		List<House> houseList = null;
		try {
			houseList = houseService.findMatchingHouse(action);
			System.out.println(houseList);
			model.addAttribute("houseList", houseList);
		} catch (Exception e) {
			// log.error("Error occurred while fetching object");
			// throw new CustomException;
			throw e;
		}
		return "index";
	}
}
