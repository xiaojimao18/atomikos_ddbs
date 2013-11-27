package com.len.trans.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.len.trans.dao.FoodDao;
import com.len.trans.pojo.Food;
import com.len.trans.service.FoodService;

@Service("foodService")
public class FoodSerivceImpl implements FoodService {
	@Autowired
	@Qualifier("foodDao")
	private FoodDao foodDao;
	
	@Override
	public List<Food> getFoodList(String restaurantId, String location) {
		List<Food> foodList = foodDao.getFoodList(restaurantId, location);
		return foodList;
	}

}
