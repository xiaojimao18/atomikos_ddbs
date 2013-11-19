package com.len.trans.service;

import java.util.List;

import com.len.trans.pojo.Food;

public interface FoodService {
	public List<Food> getFoodList(String restaurantId, String location);
}
