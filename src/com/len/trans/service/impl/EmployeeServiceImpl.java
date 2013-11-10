package com.len.trans.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.len.trans.dao.EmployeeDao;
import com.len.trans.pojo.Employee;
import com.len.trans.service.EmployeeService;
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	@Qualifier("employeeDao")
	private EmployeeDao employeeDao;
	@Override
	public List<Employee> getEmployeeList() {
		List <Employee> employeeList = new ArrayList<Employee>();
		employeeList = employeeDao.getEmployeeList(); 
		return employeeList;
	}

}
