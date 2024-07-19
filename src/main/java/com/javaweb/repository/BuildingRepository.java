package com.javaweb.repository;

import java.util.List;
import java.util.Map;

import com.javaweb.entity.BuildingEntity;

public interface BuildingRepository {
	public List<BuildingEntity> findAll(Map<String, String> requestParams, List<String> rentType);
}
