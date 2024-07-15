package com.javaweb.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.javaweb.entity.BuildingEntity;

public interface BuildingRepository {
	public List<BuildingEntity> findAll(Map<String, String> requestParams, Optional<List<String>> rentType);
}
