package com.len.trans.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.len.trans.dao.SalesReportDao;
import com.len.trans.pojo.Sale;
import com.len.trans.service.impl.DDBSDaoUtil;
@Repository("salesReportDao")
public class SalesReportDaoImpl implements SalesReportDao{
	@Autowired
	@Qualifier("ddbsDaoUtil")
	private DDBSDaoUtil ddbsDaoUtil;
	@Override
	public List<Sale> getSaleList(String location) {
		String tableName = "sale";
		List <Sale> saleList = new ArrayList<Sale>();
		String sql = "select * from " + tableName + " where Location = ?";
		final String [] fields = {"Location"};
		Object [] params = {location};
		if(location==null || location.equalsIgnoreCase("")){			
			params = null;
			sql = "select * from " + tableName;
		}
		List<JdbcTemplate> jdbcTemplateList = ddbsDaoUtil.getQueryJdbcTemplateList(tableName, fields, params);
		
		for(JdbcTemplate j : jdbcTemplateList){
			try{
				saleList.addAll(j.query(sql,params, new SaleWrapper()));
			}catch(Exception e){//如果遇到异常会继续执行(一般为站点故障或者是网络故障)
				e.printStackTrace();
			}
			
		}
		return saleList;
	}

}
