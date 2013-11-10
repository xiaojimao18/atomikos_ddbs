package com.len.trans.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.len.trans.pojo.Goods;



public class GoodsWrapper implements RowMapper<Goods>{

	@Override
	public Goods mapRow(ResultSet rs, int rowNum) throws SQLException {
		Goods goods = new Goods();
		goods.setId(new Integer(rs.getInt("Id")));
		goods.setName(rs.getString("Name"));
		//goods.setNum(rs.getString("Num"));
		goods.setPrice(new Float(rs.getFloat("Price")));
		goods.setProducer(rs.getString("PName"));
		return goods;
	}


}
