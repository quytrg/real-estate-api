package com.javaweb.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.dto.BuildingDTO;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;

@Component
public class BuildingDTOConverter {
	
//	@Autowired
//	private DistrictRepository districtRepository;
//	
//	@Autowired
//	private RentAreaRepository rentAreaRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public BuildingDTO toBuildingDTO(BuildingEntity building)  {
		BuildingDTO buildingDTO = modelMapper.map(building, BuildingDTO.class);

		buildingDTO.setAddress(building.getStreet() + ", " 
								+ building.getWard() + ", " 
								+ building.getDistrictEntity().getName());
		
		List<RentAreaEntity> rentAreaEntities = building.getRentAreaEntities();
		String rentArea = rentAreaEntities.stream().map(item -> item.getValue().toString()).collect(Collectors.joining(", "));
		buildingDTO.setRentArea(rentArea);
		
		return buildingDTO;
	}
}
