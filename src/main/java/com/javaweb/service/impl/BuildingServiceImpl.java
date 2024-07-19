package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.converter.BuildingConverter;
import com.javaweb.dto.BuildingDTO;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService{
	
	@Autowired
	private BuildingRepository buildingRepository;
	
	@Autowired
	private BuildingConverter buildingConverter;

	@Override
	public List<BuildingDTO> findAll(Map<String, String> requestParams, List<String> rentType) {
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(requestParams, rentType);
		
		List<BuildingDTO> result = new ArrayList<>();
		for(BuildingEntity building: buildingEntities) {
			BuildingDTO buildingDTO = buildingConverter.toBuildingDTO(building);
			result.add(buildingDTO);
		}
		
		return result;
	}

}
