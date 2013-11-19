package com.len.trans.dao;

import java.util.List;

import com.len.trans.pojo.Order;

public interface OrderDao {
	public List<Order> getOrderList(String userId);
	public void cancelOrder(Order order);
	public void addOrder(Order order);
}
