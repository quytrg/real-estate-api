package com.javaweb.repository.custom.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.database.MySQLConnection;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.repository.custom.RentAreaRepositoryCustom;

@Repository
public class RentAreaRepositoryCustomImpl implements RentAreaRepositoryCustom{

	@Override
	public List<RentAreaEntity> getValueByBuildingId(Integer id) {
		StringBuilder sqlString = new StringBuilder("SELECT value FROM rentarea WHERE buildingid = " + id + "; ");
		List<RentAreaEntity> result = new ArrayList<>();
		try (Connection connection = MySQLConnection.connect();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sqlString.toString());) {
			while(rs.next()) {
				RentAreaEntity rentAreaEntity = new RentAreaEntity();
				rentAreaEntity.setValue(rs.getInt("value"));
				result.add(rentAreaEntity);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return result;
	}

}
