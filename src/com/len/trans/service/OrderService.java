package com.len.trans.service;

import java.util.List;

import com.len.trans.pojo.Order;

public interface OrderService {
	public List<Order> getOrderList(String userId);
	public void addOrder (Order order);
	public void cancelOrder (Order order);
}
