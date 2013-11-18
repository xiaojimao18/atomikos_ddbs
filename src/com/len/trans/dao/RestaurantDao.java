package com.len.trans.dao;

import java.util.List;

import com.len.trans.pojo.Restaurant;

public interface RestaurantDao {
	public List<Restaurant> getRestaurantList(String location);
}
