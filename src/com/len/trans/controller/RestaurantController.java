package com.len.trans.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.len.trans.config.DataGrid;
import com.len.trans.config.JsonTransformer;
import com.len.trans.pojo.Restaurant;
import com.len.trans.service.RestaurantService;

@Controller
public class RestaurantController {

	@Autowired
	@Qualifier("restaurantService")
	private RestaurantService restService;

	@Autowired
	@Qualifier("jsonTransformer")
	private JsonTransformer jsonTransformer;
	
	@RequestMapping(value="/getRestaurant")
	public void getRestaurantList(@RequestParam String location, HttpServletResponse response) {
		List<Restaurant> restList = restService.getRestaurantList(location);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(restList);
		String jsonString = jsonTransformer.getJsonForm(dataGrid);
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().print(jsonString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
