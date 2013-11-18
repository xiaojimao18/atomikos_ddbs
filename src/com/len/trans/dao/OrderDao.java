package com.len.trans.dao;

import java.util.List;

import com.len.trans.pojo.Order;

public interface OrderDao {
	public List<Order> getOrderList(String userId);
	public void CancelOrder(String orderId);
	public void AddOrder(Order order);
}
