package com.len.trans.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.len.trans.pojo.Sale;



public class SaleWrapper implements RowMapper<Sale>{

	@Override
	public Sale mapRow(ResultSet rs, int rowNum) throws SQLException {
		Sale sale = new Sale();
		sale.setId(new Integer(rs.getInt("Id")));
		sale.setItem_id(new Integer(rs.getInt("Item_id")));
		sale.setNum(new Integer(rs.getInt("Num")));
		sale.setTime(rs.getDate("Time"));
		sale.setLocation(rs.getString("Location"));
		return sale;
	}


}
