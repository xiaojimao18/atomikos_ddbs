package com.len.trans.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.len.trans.pojo.Restaurant;

public class RestaurantWrapper implements RowMapper<Restaurant> {
	
	@Override
	public Restaurant mapRow(ResultSet rs, int rowNum) throws SQLException {
		Restaurant res = new Restaurant();
		
		res.setRestaurantId(rs.getString("RestaurantId"));
		res.setRestaurantName(rs.getString("restaurantName"));
		res.setRestaurantIntro(rs.getString("restaurantIntro"));
		res.setRestaurantImg(rs.getString("restaurantImg"));

		return res;
	}
}
