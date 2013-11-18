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

@Repository("FoodDao")
public class FoodDaoImpl implements FoodDao {
	@Autowired
	@Qualifier("ddbsDaoUtil")
	private DDBSDaoUtil ddbsDaoUtil;
	
	public List<Food> getFoodList(String RId) {
		List<Food> foodList = new ArrayList<Food>();
		
		String tableName = "food";
		String sql = "select * from " + tableName + " where restaurantId = " + RId + ";";
		//final String[] fields = {"FId", "RId", "FName", "Price", "FIntro", "FImg"};
		//Object[] params = {RId};
		
		List<JdbcTemplate> jdbcTemplateList = ddbsDaoUtil.getQueryJdbcTemplateList(tableName, null, null);
		
		for(JdbcTemplate j : jdbcTemplateList){
			foodList.addAll(j.query(sql, new FoodWrapper()));
			if(!foodList.isEmpty()) {
				break;
			}
		}
		
		return foodList;
	}
}
