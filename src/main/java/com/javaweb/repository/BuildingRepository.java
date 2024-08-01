package com.javaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.custom.BuildingRepositoryCustom;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Integer>, BuildingRepositoryCustom{
	
}
