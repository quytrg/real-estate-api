package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.database.MySQLConnection;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.BuildingRepository;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository{

	@Override
	public List<BuildingEntity> findAll(String name) {
		String sqlString = "SELECT * FROM building WHERE name like '%" + name + "%'";
		List<BuildingEntity> result = new ArrayList<>();
		try (Connection connection = MySQLConnection.connect();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sqlString);) {
			while(rs.next()) {
				BuildingEntity building = new BuildingEntity();
				building.setName(rs.getString("name"));
				building.setNumberofbasement(rs.getInt("numberofbasement"));
				building.setStreet(rs.getString("street"));
				building.setWard(rs.getString("ward"));
				result.add(building);
			}
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return result;
	}
	
}
