package com.device.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.device.model.Laptop;
import com.device.repository.LaptopRepository;

@Service
public class LaptopService {
	@Autowired
LaptopRepository laptopRepository;
	public int saveLaptop(Laptop laptop) {
		return laptopRepository.saveLaptop(laptop);
	}
	
	public List<Laptop> getLaptop(){
		return laptopRepository.getLaptop();
		
	}
	
	public List<Laptop> getLaptopById(String lapname) {
		return laptopRepository.getLaptopById(lapname);
	}
	public String updateLaptop(Laptop laptop) {
		int a = laptopRepository.updateLaptop(laptop);
		if(a ==1) 
			return "data updated into the DB successfully";
		else
			return "data not updated into DB";
	}
	public int deleteLaptop(Integer lapid) {
		return laptopRepository.deleteLaptop(lapid);
		
	}
}
