package com.len.trans.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.len.trans.pojo.Employee;



public class EmployeeWrapper implements RowMapper<Employee>{

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee employee = new Employee();
		employee.setId(new Integer(rs.getInt("Id")));
		employee.setName(rs.getString("Name"));
		employee.setWorkyear(new Integer(rs.getInt("Workyear")));
		employee.setSalary(new Integer(rs.getInt("Salary")));
		employee.setTel(rs.getString("Tel"));
		employee.setLocation(rs.getString("Location"));
		employee.setGender(rs.getString("Gender"));
		employee.setBirthday(rs.getDate("Birthday"));
		employee.setAddress(rs.getString("Address"));
		return employee;
	}


}
