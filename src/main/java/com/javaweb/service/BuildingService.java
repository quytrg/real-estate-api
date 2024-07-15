package com.javaweb.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.javaweb.dto.BuildingDTO;

public interface BuildingService {
	public List<BuildingDTO> findAll(Map<String, String> requestParams, Optional<List<String>> rentType);
}
