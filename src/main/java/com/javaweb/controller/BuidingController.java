package com.javaweb.controller;

import java.util.List;
import java.util.Map;

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
	public List<BuildingDTO> getBuidings(@RequestParam Map<String, Object> requestParams,
										@RequestParam(value = "rentType", required = false) List<String> rentType) {
		if (requestParams.containsKey("rentType")) {
			requestParams.remove("rentType");
		}
		List<BuildingDTO> result = buildingService.findAll(requestParams, rentType);
		return result;
	}
}
