package com.javaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.entity.DistrictEntity;
import com.javaweb.repository.custom.DistrictRepositoryCustom;

public interface DistrictRepository extends JpaRepository<DistrictEntity, Integer>, DistrictRepositoryCustom{
	
}
