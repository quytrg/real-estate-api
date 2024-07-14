package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.dto.BuildingDTO;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService{
	
	@Autowired
	private BuildingRepository buildingRepository;

	@Override
	public List<BuildingDTO> findAll(String name) {
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(name);
		
		List<BuildingDTO> result = new ArrayList<>();
		for(BuildingEntity building: buildingEntities) {
			BuildingDTO testBuildingDTO = new BuildingDTO();
			testBuildingDTO.setName(building.getName());
			testBuildingDTO.setNumberOfBasement(building.getNumberofbasement());
			testBuildingDTO.setAddress(building.getStreet() + " - " + building.getWard());
			result.add(testBuildingDTO);
		}
		
		return result;
	}

}
