package com.len.trans.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.len.trans.dao.RestaurantDao;
import com.len.trans.pojo.Restaurant;
import com.len.trans.service.RestaurantService;

@Service("restaurantService")
public class RestaurantServiceImpl implements RestaurantService {
	@Autowired
	@Qualifier("restaurantDao")
	private RestaurantDao restaurantDao;
	
	@Override
	public List<Restaurant> getRestaurantList(String location) {
		List<Restaurant> restList = restaurantDao.getRestaurantList(location);
		return restList;
	}

}
