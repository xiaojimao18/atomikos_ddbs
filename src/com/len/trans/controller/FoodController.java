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
import com.len.trans.pojo.Food;
import com.len.trans.service.FoodService;

@Controller
public class FoodController {
	@Autowired
	@Qualifier("jsonTransformer")
	private JsonTransformer jsonTransformer;
	
	@Autowired
	@Qualifier("foodService")
	private FoodService foodService;
	
	@RequestMapping(value="getFood")
	public void getFoodList(@RequestParam String restaurantId,
							@RequestParam String location,
							HttpServletResponse response) {
		try {
			List<Food> foodList = foodService.getFoodList(restaurantId, location);
			
			DataGrid dataGrid = new DataGrid();
			dataGrid.setRows(foodList);
			String jsonString = jsonTransformer.getJsonForm(dataGrid);
			response.setCharacterEncoding("UTF-8");
		
			response.getWriter().print(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
