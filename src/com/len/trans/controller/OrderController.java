package com.len.trans.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
	@Qualifier("orderService")
	private OrderService orderService;
	
	@RequestMapping(value="getOrders")
	public void getOrderList(@RequestParam String userId, HttpServletResponse response) {
		try {
			List<Order> orderList = orderService.getOrderList(userId);
			
			DataGrid dataGrid = new DataGrid();
			dataGrid.setRows(orderList);
			String jsonString = jsonTransformer.getJsonForm(dataGrid);
			response.setCharacterEncoding("UTF-8");
		
			response.getWriter().print(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="cancelOrder")
	public void cancelOrder(@RequestParam String orderId, 
							@RequestParam String location, 
							HttpServletResponse response) {
		Order order = new Order();
		
		order.setOrderId(orderId);
		order.setLocation(location);
		
		orderService.cancelOrder(order);
	}
	
	@RequestMapping(value="addOrder")
	public void addOrder(	@RequestParam String userId,
							@RequestParam String foodId,
							@RequestParam String restaurantId,
							@RequestParam String price,
							@RequestParam String location, 
							@RequestParam String number,
							@RequestParam String state,
							HttpServletResponse response) {
		Order order = new Order();
		
		order.setOrderId(userId);
		order.setFoodId(foodId);
		order.setRestaurantId(restaurantId);
		order.setPrice(price);
		order.setLocation(location);
		order.setNumber(Integer.parseInt(number));
		order.setState(state);
		
		order.setDate(new Date());
		order.setOrderId(UUID.randomUUID().toString());
		
		orderService.addOrder(order);
	}
}
