package com.javaweb.converter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.utils.MapUtil;

@Component
public class BuildingSearchBuilderConverter {
	public BuildingSearchBuilder toBuildingSearchBuilder(Map<String, Object> requestParams, List<String> rentType) {
		BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
				.setName(MapUtil.getObject(requestParams, "name", String.class))
				.setFloorArea(MapUtil.getObject(requestParams, "floorArea", Integer.class))
				.setDistrictId(MapUtil.getObject(requestParams, "districtId", Integer.class))
				.setWard(MapUtil.getObject(requestParams, "ward", String.class))
				.setStreet(MapUtil.getObject(requestParams, "street", String.class))
				.setNumberOfBasement(MapUtil.getObject(requestParams, "numberOfBasement", Integer.class))
				.setDirection(MapUtil.getObject(requestParams, "direction", String.class))
				.setLevel(MapUtil.getObject(requestParams, "level", String.class))
				.setRentAreaFrom(MapUtil.getObject(requestParams, "rentAreaFrom", Integer.class))
				.setRentAreaTo(MapUtil.getObject(requestParams, "rentAreaTo", Integer.class))
				.setRentPriceFrom(MapUtil.getObject(requestParams, "rentPriceFrom", Integer.class))
				.setRentPriceTo(MapUtil.getObject(requestParams, "rentPriceTo", Integer.class))
				.setManagerName(MapUtil.getObject(requestParams, "managerName", String.class))
				.setManagerPhoneNumber(MapUtil.getObject(requestParams, "managerPhoneNumber", String.class))
				.setStaffId(MapUtil.getObject(requestParams, "staffId", Integer.class))
				.setRentType(rentType)
				.build();
		return buildingSearchBuilder;
	}
}
