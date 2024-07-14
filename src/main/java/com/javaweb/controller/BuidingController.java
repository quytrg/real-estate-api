package com.javaweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.dto.BuildingDTO;
import com.javaweb.service.BuildingService;

@RestController
@RequestMapping(value = "/api/buildings", produces = MediaType.APPLICATION_JSON_VALUE)
public class BuidingController {
	
	@Autowired
	private BuildingService buildingService;
	
	@GetMapping
	public List<BuildingDTO> getBuidings(@RequestParam String name) {
		List<BuildingDTO> result = buildingService.findAll(name);
		return result;
	}
}
