package com.len.trans.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.len.trans.dao.OrderDao;
import com.len.trans.pojo.Goods;
import com.len.trans.pojo.Order;
import com.len.trans.service.impl.DDBSDaoUtil;

@Repository("orderDao")
public class OrderDaoImpl implements OrderDao {
	
	@Autowired
	@Qualifier("ddbsDaoUtil")
	private DDBSDaoUtil ddbsDaoUtil;
	
	@Override
	public List<Order> getOrderList(String userId) {
		List<Order> orderList = new ArrayList<Order>();

		String tableName  = "orders";
		List<JdbcTemplate> jdbcTemplateList = ddbsDaoUtil.getQueryJdbcTemplateList(tableName, null, null);

		String sql = "select * " + tableName + " where UId = " + userId + ";";
		for(JdbcTemplate j : jdbcTemplateList){
			orderList.addAll(j.query(sql, new OrderWrapper()));
			if(!orderList.isEmpty()) {
				break;
			}
		}
		
		return orderList;
	}

	@Override
	public void CancelOrder(String orderId) {
		String tableName  = "orders";

		String [] fields = {"OId"};
		Object[] params={orderId}; //注意这个的param 与 field 有对应
		
		List <JdbcTemplate> jdbcTemplateList = ddbsDaoUtil.getUpdateJdbcTemplateList(tableName, fields, params);
	
		String sql = "delete from "+ tableName +" where OId = ?;";
		for(JdbcTemplate j :jdbcTemplateList){
			j.update(sql, params);
		}
	}

	@Override
	public void AddOrder(Order order) {
		String tableName  = "orders";

		String [] fields = {"OId","UId","FId","RId","Number","Price","Location","Time","State"};
		Object[] params={order.getOrderId(), order.getUserId(), order.getFoodId(), order.getRestaurantId(), 
						order.getNumber(), order.getPrice(), order.getLocation(), order.getDate(),
						order.getState()}; //注意这个的param 与 field 有对应
		
		String fields_str = ddbsDaoUtil.getFieldsStr(fields);
		List <JdbcTemplate> jdbcTemplateList = ddbsDaoUtil.getUpdateJdbcTemplateList(tableName, fields, params);
	
		String sql = "insert into "+ tableName +"("+ fields_str +") values(?,?,?,?,?,?,?,?,?);";
		for(JdbcTemplate j :jdbcTemplateList){
			j.update(sql, params);
		}
	}

}
