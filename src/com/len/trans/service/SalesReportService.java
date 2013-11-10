package com.len.trans.service;

import java.util.List;

import com.len.trans.pojo.Sale;

public interface SalesReportService {
	public List<Sale> getSalesReport(String location);
}
