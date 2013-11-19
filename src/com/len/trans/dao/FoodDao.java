package com.len.trans.dao;

import java.util.List;

import com.len.trans.pojo.Food;

public interface FoodDao {
	public List<Food> getFoodList(String RId, String location);
}
