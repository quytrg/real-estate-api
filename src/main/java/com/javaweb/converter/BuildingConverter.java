package com.javaweb.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.dto.BuildingDTO;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.DistrictEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;

@Component
public class BuildingConverter {
	
	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private RentAreaRepository rentAreaRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public BuildingDTO toBuildingDTO(BuildingEntity building)  {
		BuildingDTO buildingDTO = modelMapper.map(building, BuildingDTO.class);

		DistrictEntity district = districtRepository.findOneById(building.getDistrictId());
		buildingDTO.setAddress(building.getStreet() + ", " + building.getWard() + ", " + district.getName());
		
		List<RentAreaEntity> rentAreaEntities = rentAreaRepository.getValueByBuildingId(building.getId());
		String rentArea = rentAreaEntities.stream().map(item -> item.getValue().toString()).collect(Collectors.joining(", "));
		buildingDTO.setRentArea(rentArea);
		
//		buildingDTO.setName(building.getName());
//		buildingDTO.setNumberOfBasement(building.getNumberofbasement());
//		buildingDTO.setManagerName(building.getManagerName());
//		buildingDTO.setManagerPhoneNumber(building.getManagerPhoneNumber());
//		buildingDTO.setFloorArea(building.getFloorArea());
//		buildingDTO.setRentPrice(building.getRentPrice());
//		buildingDTO.setServiceFee(building.getServiceFee());
//		buildingDTO.setBrokerageFee(building.getBrokerageFee());
		
		return buildingDTO;
	}
}
