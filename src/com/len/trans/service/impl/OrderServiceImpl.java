package com.len.trans.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.len.trans.dao.OrderDao;
import com.len.trans.pojo.Order;
import com.len.trans.service.OrderService;

@Service("orderSerivce")
public class OrderServiceImpl implements OrderService {
	@Autowired
	@Qualifier("orderDao")
	private OrderDao orderDao;
	
	@Override
	public List<Order> getOrderList(String userId) {
		List<Order> orderList = orderDao.getOrderList(userId);
		return orderList;
	}

	@Override
	public void addOrder(Order order) {
		orderDao.addOrder(order);
	}

	@Override
	public void cancelOrder(Order order) {
		orderDao.cancelOrder(order);
	}

}
