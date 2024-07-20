package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.database.MySQLConnection;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.utils.NumberUtil;
import com.javaweb.utils.StringUtil;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository{

	public static void joinTable(Map<String, String> requestParams, List<String> rentType, StringBuilder sqlString) {
		String staffId = requestParams.get("staffId");
		if (StringUtil.isValidRequestParam(staffId)) {
			sqlString.append(" INNER JOIN assignmentbuilding ab ON b.id = ab.buildingid ");
		}
		
		if (rentType != null && !rentType.stream().allMatch(n -> n.trim().isEmpty())) {
			sqlString.append(" INNER JOIN buildingrenttype br ON b.id = br.buildingid ");
			sqlString.append(" INNER JOIN renttype rt ON br.renttypeid = rt.id ");
		}
	}
	
	public static void normalQuery(Map<String, String> requestParams, List<String> rentType, StringBuilder whereString) {
		for (Map.Entry<String, String> item: requestParams.entrySet()) {
			// except "staffId", "rentType", "rentArea...", "rentPrice..."
			if (!item.getKey().equals("staffId") && !item.getKey().startsWith("rent")) {
				if (StringUtil.isValidRequestParam(item.getValue())) {
					if (NumberUtil.isInteger(item.getValue())) {
						whereString.append(" AND b." + item.getKey().toLowerCase() + " = " + item.getValue() + " ");
					}
					else {

						whereString.append(" AND b." + item.getKey().toLowerCase() + " LIKE '%" + item.getValue() + "%' ");
					}
				}
			}
		}
	}
	
	public static void specialQuery(Map<String, String> requestParams, List<String> rentType, StringBuilder whereString) {
		String staffId = requestParams.get("staffId");
		if (StringUtil.isValidRequestParam(staffId)) {
			whereString.append(" AND ab.staffid = " + staffId + " ");
		}
		
		if (rentType != null && !rentType.stream().allMatch(n -> n.trim().isEmpty())) {
			whereString.append(" AND rt.code IN ( ");
			for (String code : rentType) {
				whereString.append("'" + code + "', ");
			}
			whereString.deleteCharAt(whereString.lastIndexOf(","));
			whereString.append(") ");
		}
		
		String rentAreaFrom = requestParams.get("rentAreaFrom");
		String rentAreaTo = requestParams.get("rentAreaTo");
		if (StringUtil.isValidRequestParam(rentAreaFrom) || StringUtil.isValidRequestParam(rentAreaTo)) {
			whereString.append(" AND EXISTS ( SELECT 1 FROM rentarea ra WHERE b.id = ra.buildingid ");
			if (StringUtil.isValidRequestParam(rentAreaFrom)) {
				whereString.append(" AND ra.value >= " + rentAreaFrom + " ");
			}
			if (StringUtil.isValidRequestParam(rentAreaTo)) {
				whereString.append(" AND ra.value <= " + rentAreaTo + " ");
			}
			whereString.append(" ) ");
		}
		
		
		String rentPriceFrom = requestParams.get("rentPriceFrom");
		String rentPriceTo = requestParams.get("rentPriceTo");
		if (StringUtil.isValidRequestParam(rentPriceFrom)) {
			whereString.append(" AND b.rentprice >= " + rentPriceFrom + " ");
		}
		if (StringUtil.isValidRequestParam(rentPriceTo)) {
			whereString.append(" AND b.rentprice <= " + rentPriceTo + " ");
		}
	}
	
	@Override
	public List<BuildingEntity> findAll(Map<String, String> requestParams, List<String> rentType) {
		StringBuilder sqlString = new StringBuilder("SELECT DISTINCT b.id, b.name, b.numberofbasement, b.ward, b.street, b.districtid, "
													+ " b.managername, b.managerphonenumber, b.floorarea, b.rentprice, b.servicefee, b.brokeragefee " 
													+ " FROM building b ");
		
		joinTable(requestParams, rentType, sqlString);
		
		StringBuilder whereString = new StringBuilder(" WHERE 1 = 1 ");
		normalQuery(requestParams, rentType, whereString);
		specialQuery(requestParams, rentType, whereString);
		
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
				building.setDistrictId(rs.getInt("districtid"));
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
