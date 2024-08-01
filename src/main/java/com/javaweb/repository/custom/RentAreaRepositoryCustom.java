package com.javaweb.repository.custom;

import java.util.List;

import com.javaweb.entity.RentAreaEntity;

public interface RentAreaRepositoryCustom {
	public List<RentAreaEntity> getValueByBuildingId(Integer id);
}
