package com.len.trans.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.len.trans.dao.GoodsDao;
import com.len.trans.pojo.Goods;
import com.len.trans.service.impl.DDBSDaoUtil;

@Repository("goodsDao")
public class GoodsDaoImpl implements GoodsDao{
	@Autowired
	@Qualifier("ddbsDaoUtil")
	private DDBSDaoUtil ddbsDaoUtil;
	final String [] fields = {"Id","Name","Num","Price"};
	@Override
	public List<Goods> getGoodsList() {
		String tableName  = "storage";
		//暂时只考虑水平分片，水平分片的插入要找到到底要插入的表，然后
		//可能要执行多条插入操作
		
		List<Goods> goodsList = new ArrayList<Goods>();
		String sql = "select s.Id, s.Name, s.Price, p.PName from " + tableName + " s ,producer p where s.ProducerId = p.Id;";
		List<JdbcTemplate> jdbcTemplateList = ddbsDaoUtil.getQueryJdbcTemplateList(tableName, null, null);
		
		for(JdbcTemplate j : jdbcTemplateList){
			goodsList.addAll(j.query(sql, new GoodsWrapper()));
			if(!goodsList.isEmpty()) {
				break;
			}
		}
		
		return goodsList;
	}
	@Override
	public void updatePrice(int goodsId, float goodsPrice) throws Exception {
		String tableName  = "storage";
		String sql = "UPDATE " + tableName +" SET Price = ? WHERE Id = ?";
		final String [] fields = {"Price","Id"};
		Object  [] params = {goodsPrice, goodsId};
		
		List<JdbcTemplate> jdbcTemplateList = ddbsDaoUtil.getUpdateJdbcTemplateList(tableName, fields, params);
		for(JdbcTemplate j :jdbcTemplateList){
			j.update(sql, params);
		}
	}

}
