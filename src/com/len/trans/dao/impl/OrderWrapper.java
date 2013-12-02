package com.len.trans.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.len.trans.pojo.Order;

public class OrderWrapper implements RowMapper<Order> {

	@Override
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		Order order = new Order();
		
		order.setOrderId(rs.getString("OId"));
		order.setUserId(rs.getString("UId"));
		order.setFoodId(rs.getString("FId"));
		order.setFoodName(rs.getString("FName"));
		order.setRestaurantId(rs.getString("RId"));
		order.setRestaurantName(rs.getString("RName"));
		order.setNumber(new Integer(rs.getInt("Number")));
		order.setPrice(rs.getString("Price"));
		order.setLocation(rs.getString("Location"));
		order.setDate(rs.getDate("Time"));
		order.setState(rs.getString("State"));
		
		return order;
	}

}
