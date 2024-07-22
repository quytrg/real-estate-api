package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingDTOConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.dto.BuildingDTO;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService{
	
	@Autowired
	private BuildingRepository buildingRepository;
	
	@Autowired
	private BuildingDTOConverter buildingDTOConverter;
	
	@Autowired
	private BuildingSearchBuilderConverter buildingSearchBuilderConverter;

	@Override
	public List<BuildingDTO> findAll(Map<String, Object> requestParams, List<String> rentType) {
		
		BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter
													.toBuildingSearchBuilder(requestParams, rentType);
		
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSearchBuilder);
		
		List<BuildingDTO> result = new ArrayList<>();
		for(BuildingEntity building: buildingEntities) {
			BuildingDTO buildingDTO = buildingDTOConverter.toBuildingDTO(building);
			result.add(buildingDTO);
		}
		
		return result;
	}

}
