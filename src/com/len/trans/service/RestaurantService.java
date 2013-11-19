package com.len.trans.service;

import java.util.List;

import com.len.trans.pojo.Restaurant;

public interface RestaurantService {
	public List<Restaurant> getRestaurantList(String location);
}
