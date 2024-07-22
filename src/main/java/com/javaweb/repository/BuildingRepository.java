package com.javaweb.repository;

import java.util.List;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;

public interface BuildingRepository {
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder);
}
