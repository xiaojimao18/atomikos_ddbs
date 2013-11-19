package com.len.trans.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.len.trans.dao.FoodDao;
import com.len.trans.pojo.Food;
import com.len.trans.service.impl.DDBSDaoUtil;

@Repository("foodDao")
public class FoodDaoImpl implements FoodDao {
	@Autowired
	@Qualifier("ddbsDaoUtil")
	private DDBSDaoUtil ddbsDaoUtil;
	
	public List<Food> getFoodList(String restaurantId, String location) {
		List<Food> foodList = new ArrayList<Food>();
		
		String tableName = "food";
		String[] fields = {"Location"};
		Object[] params = {location};
		
		List<JdbcTemplate> jdbcTemplateList = ddbsDaoUtil.getQueryJdbcTemplateList(tableName, fields, params);
		
		String sql = "select * from " + tableName + " where restaurantId = " + restaurantId;
		for(JdbcTemplate j : jdbcTemplateList){
			foodList.addAll(j.query(sql, new FoodWrapper()));
			if(!foodList.isEmpty()) {
				break;
			}
		}
		
		return foodList;
	}
}
