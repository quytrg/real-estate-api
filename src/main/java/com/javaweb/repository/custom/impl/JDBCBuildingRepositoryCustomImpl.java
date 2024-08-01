package com.javaweb.repository.custom.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.database.MySQLConnection;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.custom.BuildingRepositoryCustom;

@Repository
public class JDBCBuildingRepositoryCustomImpl implements BuildingRepositoryCustom{
	
	public static void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sqlString) {
		Integer staffId = buildingSearchBuilder.getStaffId();
		if (staffId != null) {
			sqlString.append(" INNER JOIN assignmentbuilding ab ON b.id = ab.buildingid ");
		}
		List<String> rentType = buildingSearchBuilder.getRentType();
		if (rentType != null && !rentType.stream().allMatch(n -> n.trim().isEmpty())) {
			sqlString.append(" INNER JOIN buildingrenttype br ON b.id = br.buildingid ");
			sqlString.append(" INNER JOIN renttype rt ON br.renttypeid = rt.id ");
		}
	}
	
	public static void normalQuery(BuildingSearchBuilder buildingSearchBuilder, StringBuilder whereString) {
//		for (Map.Entry<String, String> item: requestParams.entrySet()) {
//			// except "staffId", "rentType", "rentArea...", "rentPrice..."
//			if (!item.getKey().equals("staffId") && !item.getKey().startsWith("rent")) {
//				if (StringUtil.isValidRequestParam(item.getValue())) {
//					if (NumberUtil.isInteger(item.getValue())) {
//						whereString.append(" AND b." + item.getKey().toLowerCase() + " = " + item.getValue() + " ");
//					}
//					else {
//
//						whereString.append(" AND b." + item.getKey().toLowerCase() + " LIKE '%" + item.getValue() + "%' ");
//					}
//				}
//			}
//		}
		
		try {
			Field[] fields = BuildingSearchBuilder.class.getFields();
			for (Field item : fields) {
				String key = item.getName();
				// except "staffId", "rentType", "rentArea...", "rentPrice..."
				if (!key.equals("staffId") && !key.startsWith("rent")) {
					Object value = item.get(buildingSearchBuilder);
					if (value != null) {
						if (item.getType().equals(Integer.class) || item.getType().equals(Double.class)) {
							whereString.append(" AND b." + key.toLowerCase() + " = " + value + " ");
						}
						else if (item.getType().equals(String.class)){
							whereString.append(" AND b." + key.toLowerCase() + " LIKE '%" + value + "%' ");
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
			whereString.append(" AND ab.staffid = " + staffId + " ");
		}
		
		List<String> rentType = buildingSearchBuilder.getRentType();
		if (rentType != null && !rentType.stream().allMatch(n -> n.trim().isEmpty())) {
//			c1
//			whereString.append(" AND rt.code IN ( ");
//			for (String code : rentType) {
//				whereString.append("'" + code + "', ");
//			}
//			whereString.deleteCharAt(whereString.lastIndexOf(","));
//			whereString.append(") ");
//			c2
			whereString.append(" AND rt.code IN ( "
					+ rentType.stream().map(item -> "'" + item + "'").collect(Collectors.joining(", ")) 
					+ " ) ");
		}
		
		Integer rentAreaFrom = buildingSearchBuilder.getRentAreaFrom();
		Integer rentAreaTo = buildingSearchBuilder.getRentAreaTo();
		if (rentAreaFrom != null || rentAreaTo != null) {
			whereString.append(" AND EXISTS ( SELECT 1 FROM rentarea ra WHERE b.id = ra.buildingid ");
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
			whereString.append(" AND b.rentprice >= " + rentPriceFrom + " ");
		}
		if (rentAreaTo != null) {
			whereString.append(" AND b.rentprice <= " + rentPriceTo + " ");
		}
	}
	
	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
		StringBuilder sqlString = new StringBuilder("SELECT DISTINCT b.id, b.name, b.numberofbasement, b.ward, b.street, b.districtid, "
													+ " b.managername, b.managerphonenumber, b.floorarea, b.rentprice, b.servicefee, b.brokeragefee " 
													+ " FROM building b ");
		
		joinTable(buildingSearchBuilder, sqlString);
		
		StringBuilder whereString = new StringBuilder(" WHERE 1 = 1 ");
		normalQuery(buildingSearchBuilder, whereString);
		specialQuery(buildingSearchBuilder, whereString);
		
		sqlString.append(whereString);
		
		List<BuildingEntity> result = new ArrayList<>();
		try (Connection connection = MySQLConnection.connect();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sqlString.toString());) {
			while(rs.next()) {
				BuildingEntity building = new BuildingEntity();
				building.setId(rs.getInt("id"));
				building.setName(rs.getString("name"));
				building.setNumberofbasement(rs.getInt("numberofbasement"));
				building.setStreet(rs.getString("street"));
				building.setWard(rs.getString("ward"));
//				building.setDistrictId(rs.getInt("districtid"));
				building.setManagerName(rs.getString("managername"));
				building.setManagerPhoneNumber(rs.getString("managerphonenumber"));
				building.setFloorArea(rs.getInt("floorarea"));
				building.setRentPrice(rs.getInt("rentprice"));
				building.setServiceFee(rs.getInt("servicefee"));
				building.setBrokerageFee(rs.getInt("brokeragefee"));
				result.add(building);
			}
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return result;
	}
	
}
