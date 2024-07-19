package com.javaweb.repository;

import java.util.List;

import com.javaweb.entity.RentAreaEntity;

public interface RentAreaRepository {
	public List<RentAreaEntity> getValueByBuildingId(Integer id);
}
