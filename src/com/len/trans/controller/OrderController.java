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
import com.len.trans.pojo.Order;
import com.len.trans.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	@Qualifier("jsonTransformer")
	private JsonTransformer jsonTransformer;
	
	@Autowired
	@Qualifier("restaurantService")
	private OrderService orderService;
	
	@RequestMapping(value="getOrders")
	public void getOrderList(@RequestParam String userId, HttpServletResponse response) {
		List<Order> orderList = orderService.getOrderList(userId);
		
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(orderList);
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
