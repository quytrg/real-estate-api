package com.javaweb.service;

import java.util.List;
import java.util.Map;

import com.javaweb.dto.BuildingDTO;

public interface BuildingService {
	public List<BuildingDTO> findAll(Map<String, String> requestParams, List<String> rentType);
}
