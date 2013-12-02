package com.len.trans.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	
	@RequestMapping(value="/getFood")
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
	
	@RequestMapping(value="/getFoods")
	public String getFoods(//@RequestParam String restaurantId,
							HttpServletResponse response, HttpServletRequest request) {
		//String restID = request.getParameter("restaurantId");
		HttpSession session = request.getSession(true);
		//session.setAttribute("restID", restaurantId);
		//request.setAttribute("location", session.getAttribute("location"));
		response.setContentType("text/html; charset=utf-8");

		//ServletContext sc = request.getServletContext();
		//RequestDispatcher rd = null;
		//rd = sc.getRequestDispatcher("jjhs"); //定向的页面
		//try {
		//	rd.forward(request, response);
		//} catch (ServletException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		return "jjhs";		
	}
}
