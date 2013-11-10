package com.len.trans.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.len.trans.dao.SalesReportDao;
import com.len.trans.pojo.Sale;
import com.len.trans.service.SalesReportService;

@Service("salesReportService")
public class SalesReportServiceImpl implements SalesReportService{
	@Autowired
	@Qualifier("salesReportDao")
	private SalesReportDao salesReportDao;

	@Override
	public List<Sale> getSalesReport(String location) {
		return salesReportDao.getSaleList(location);
	}
}
