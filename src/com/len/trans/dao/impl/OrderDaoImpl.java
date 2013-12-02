package com.len.trans.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.len.trans.dao.OrderDao;
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

		String sql = "select OId, o.UId, o.FId, f.FName, o.RId, r.RName, Number, Price, o.Location, Time, State from "
					+ tableName + " o, food f, restaurant r where o.UId = '" + userId + "' and o.RId = r.RId, and o.FId = f.FId";
		for(JdbcTemplate j : jdbcTemplateList){
			orderList.addAll(j.query(sql, new OrderWrapper()));
			if(!orderList.isEmpty()) {
				break;
			}
		}
		
		return orderList;
	}

	@Override
	public void cancelOrder(Order order) {
		String tableName  = "orders";

		// 修改已分片的表格时，fields必须包含分片规则的属性，params中对应着该字段的值
		String [] fields = {"Location"};
		Object[] params={order.getLocation()};
		
		List <JdbcTemplate> jdbcTemplateList = ddbsDaoUtil.getUpdateJdbcTemplateList(tableName, fields, params);
	
		String sql = "delete from " + tableName + " where OId = " + order.getOrderId();
		for(JdbcTemplate j :jdbcTemplateList){
			j.update(sql);
		}
	}

	@Override
	public void addOrder(Order order) {
		String tableName  = "orders";

		// 修改已分片的表格时，fields必须包含分片规则的属性，params中对应着该字段的值
		String [] fields = {"OId","UId","FId","RId","Number","Price","Location","Time","State"};
		Object[] params={order.getOrderId(), order.getUserId(), order.getFoodId(), order.getRestaurantId(), 
						order.getNumber(), order.getPrice(), order.getLocation(), order.getDate(),
						order.getState()};
		
		List <JdbcTemplate> jdbcTemplateList = ddbsDaoUtil.getUpdateJdbcTemplateList(tableName, fields, params);
	
		String fields_str = ddbsDaoUtil.getFieldsStr(fields);
		String sql = "insert into "+ tableName +"("+ fields_str +") values(?,?,?,?,?,?,?,?,?)";
		for(JdbcTemplate j :jdbcTemplateList){
			j.update(sql, params);
		}
	}

}
