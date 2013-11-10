package com.len.trans.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.len.trans.config.DataGrid;
import com.len.trans.config.JsonTransformer;
import com.len.trans.pojo.Employee;
import com.len.trans.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeManagementController {
	@Autowired
	@Qualifier("employeeService")
	private EmployeeService employeeService;
	
	@Autowired
	@Qualifier("jsonTransformer")
	private JsonTransformer jsonTransformer;
	
	@RequestMapping(value = "/management")
	public String employeeManagement(){
		return "/employee/employeesManagement";
	}
	@RequestMapping(value = "/employeeList/all")
	public void getAllEmployees(HttpServletResponse response) throws IOException{
		List<Employee> list = employeeService.getEmployeeList();
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(list);
		String jsonString = jsonTransformer.getJsonForm(dataGrid);
		response.setCharacterEncoding("UTF-8");
		//response.setContentType("application/json");
		response.getWriter().print(jsonString);
	}
}
