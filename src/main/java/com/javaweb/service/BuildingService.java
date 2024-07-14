package com.javaweb.service;

import java.util.List;

import com.javaweb.dto.BuildingDTO;

public interface BuildingService {
	public List<BuildingDTO> findAll(String name);
}
