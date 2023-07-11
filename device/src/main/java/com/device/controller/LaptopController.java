package com.device.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.device.model.Laptop;
import com.device.service.LaptopService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/laptop")
public class LaptopController {
	@Autowired
	LaptopService laptopService;

	@PostMapping
	public String saveLaptop(@RequestBody Laptop laptop) {
		int value = 0;
		value = laptopService.saveLaptop(laptop);
		if (value == 1)
			return "data inserted into DB successfully " + value;
		else
			return "data not inserted into DB";
	}

	@GetMapping
	public List<Laptop> getLaptop() {
		return laptopService.getLaptop();

	}
	@GetMapping("/{lapname}")
	public List<Laptop> getLaptopById(@PathVariable String lapname) {
		return laptopService.getLaptopById(lapname);
	}
	@PutMapping
	public String updateLaptop(@RequestBody Laptop laptop) {
		return laptopService.updateLaptop(laptop);
	}
	
	@DeleteMapping("/{lapid}")
	public String deleteLaptop(@PathVariable Integer lapid) {
		int a = laptopService.deleteLaptop(lapid);
		if(a ==1)
			return "data deleted from table successfully "+a;
		else
			return "data not deleted from table";
	}
}
