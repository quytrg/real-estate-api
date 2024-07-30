package com.javaweb.repository.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.BuildingRepository;

@Repository
@Primary
public class BuildingRepositoryImpl implements BuildingRepository{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public static void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sqlString) {
		Integer staffId = buildingSearchBuilder.getStaffId();
		if (staffId != null) {
			sqlString.append(" JOIN b.assignmentBuildingEntities ab ");
		}
		List<String> rentType = buildingSearchBuilder.getRentType();
		if (rentType != null && !rentType.stream().allMatch(n -> n.trim().isEmpty())) {
			sqlString.append(" JOIN b.rentTypeEntities rt ");
		}
	}
	
	public static void normalQuery(BuildingSearchBuilder buildingSearchBuilder, StringBuilder whereString) {
		try {
			Field[] fields = BuildingSearchBuilder.class.getFields();
			for (Field item : fields) {
				String key = item.getName();
				// except "staffId", "rentType", "rentArea...", "rentPrice..."
				if (!key.equals("staffId") && !key.startsWith("rent")) {
					Object value = item.get(buildingSearchBuilder);
					if (value != null) {
						if (item.getType().equals(Integer.class) || item.getType().equals(Double.class)) {
							whereString.append(" AND b." + key + " = " + value + " ");
						}
						else if (item.getType().equals(String.class)){
							whereString.append(" AND b." + key + " LIKE '%" + value + "%' ");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void specialQuery(BuildingSearchBuilder buildingSearchBuilder, StringBuilder whereString) {
		Integer staffId = buildingSearchBuilder.getStaffId();
		if (staffId != null) {
			whereString.append(" AND ab.userEntity.id = " + staffId + " ");
		}
		
		List<String> rentType = buildingSearchBuilder.getRentType();
		if (rentType != null && !rentType.stream().allMatch(n -> n.trim().isEmpty())) {
			whereString.append(" AND rt.code IN ( "
					+ rentType.stream().map(item -> "'" + item + "'").collect(Collectors.joining(", ")) 
					+ " ) ");
		}
		
		Integer rentAreaFrom = buildingSearchBuilder.getRentAreaFrom();
		Integer rentAreaTo = buildingSearchBuilder.getRentAreaTo();
		if (rentAreaFrom != null || rentAreaTo != null) {
			whereString.append(" AND EXISTS ( SELECT 1 FROM b.rentAreaEntities ra WHERE 1 = 1 ");
			if (rentAreaFrom != null) {
				whereString.append(" AND ra.value >= " + rentAreaFrom + " ");
			}
			if (rentAreaTo != null) {
				whereString.append(" AND ra.value <= " + rentAreaTo + " ");
			}
			whereString.append(" ) ");
		}
		
		
		Integer rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
		Integer rentPriceTo = buildingSearchBuilder.getRentPriceTo();
		if (rentPriceFrom != null) {
			whereString.append(" AND b.rentPrice >= " + rentPriceFrom + " ");
		}
		if (rentAreaTo != null) {
			whereString.append(" AND b.rentPrice <= " + rentPriceTo + " ");
		}
	}
	
	@Override
	@Transactional
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
		// JPQL
		StringBuilder sqlString = new StringBuilder("SELECT DISTINCT b FROM BuildingEntity b ");
		
		joinTable(buildingSearchBuilder, sqlString);
		
		StringBuilder whereString = new StringBuilder(" WHERE 1 = 1 ");
		normalQuery(buildingSearchBuilder, whereString);
		specialQuery(buildingSearchBuilder, whereString);
		
		sqlString.append(whereString);
		
		System.out.println(sqlString);
		
		TypedQuery<BuildingEntity> query = entityManager.createQuery(sqlString.toString(), BuildingEntity.class);
		return query.getResultList();
	}
	
}
