package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.javaweb.database.MySQLConnection;
import com.javaweb.entity.DistrictEntity;
import com.javaweb.repository.DistrictRepository;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository{

	@Override
	public DistrictEntity findOneById(Integer id) {
		StringBuilder sqlString = new StringBuilder("SELECT * FROM district WHERE id = " + id + "; ");
		
		DistrictEntity result = new DistrictEntity();
		try (Connection connection = MySQLConnection.connect();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sqlString.toString());) {
			rs.next();
			result.setId(rs.getInt("id"));
			result.setCode(rs.getString("code"));
			result.setName(rs.getString("name"));
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return result;
	}
	
}
