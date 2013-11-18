package com.len.trans.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.len.trans.pojo.Food;

public class FoodWrapper implements RowMapper<Food> {

	@Override
	public Food mapRow(ResultSet rs, int rowNum) throws SQLException {
		Food food = new Food();
		
		food.setFoodId(rs.getString("FId"));
		food.setRestaurantId(rs.getString("RId"));
		food.setFoodName(rs.getString("FName"));
		food.setPrice(rs.getString("price"));
		food.setFoodIntro(rs.getString("FIntro"));
		food.setFoodImg(rs.getString("FImg"));

		return food;
	}

}
