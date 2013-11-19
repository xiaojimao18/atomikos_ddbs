package com.len.trans.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.len.trans.config.DataGrid;
import com.len.trans.config.JsonTransformer;
import com.len.trans.pojo.Sale;
import com.len.trans.service.SalesReportService;

@Controller
@RequestMapping("reports")
public class SalesReportController {
	@Autowired
	@Qualifier("salesReportService")
	private SalesReportService salesReportService;
	
	@Autowired
	@Qualifier("jsonTransformer")
	private JsonTransformer jsonTransformer;
	
	@RequestMapping(value = "/salesReport")
	public String saleReport(){
		return "/report/salesReport";
	}
	
	@RequestMapping(value = "/salesReport/all")
	public void getSalesReport(HttpServletResponse response, @RequestParam(defaultValue = "", required = false) String location) throws IOException{
		List<Sale> list = salesReportService.getSalesReport(location);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(list);
		String jsonString = jsonTransformer.getJsonForm(dataGrid);
		response.setCharacterEncoding("UTF-8");
		//response.setContentType("application/json");
		response.getWriter().print(jsonString);
	}
}
