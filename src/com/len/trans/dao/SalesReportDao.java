package com.len.trans.dao;

import java.util.List;

import com.len.trans.pojo.Sale;

public interface SalesReportDao {
	public List<Sale> getSaleList(String location);
}
