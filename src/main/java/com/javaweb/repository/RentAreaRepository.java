package com.javaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.entity.RentAreaEntity;
import com.javaweb.repository.custom.RentAreaRepositoryCustom;

public interface RentAreaRepository extends JpaRepository<RentAreaEntity, Integer>, RentAreaRepositoryCustom{
	
}
