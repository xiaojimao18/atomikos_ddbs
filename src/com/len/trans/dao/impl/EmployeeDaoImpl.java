package com.len.trans.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.len.trans.dao.EmployeeDao;
import com.len.trans.pojo.Employee;
import com.len.trans.service.impl.DDBSDaoUtil;
@Repository("employeeDao")
public class EmployeeDaoImpl implements EmployeeDao{
	
	@Autowired
	@Qualifier("ddbsDaoUtil")
	private DDBSDaoUtil ddbsDaoUtil;
	
	@Override
	public List<Employee> getEmployeeList() {
		String tableName = "employee";
		List <Employee> employeeList = new ArrayList<Employee>();
		String sql = "select * from "+tableName;
		final String [] fields = {"Id","Name","Workyear","Salary","Tel","Location","Gender","Birthday","Address"};
		/**
		 * 如果这是总部：
		 * 总部保存着多有的雇员
		 * 但是可能总部数据库挂掉
		 * 就访问各个站点的数据
		 * 如果是修改的话就要 维护数据的一致性
		 * 
		 * 访问策略
		 * 访问自己本地的数据库，如果返回的结果为空，找所有的子站点，去并集
		 * 
		 * 插入策略，主站点没有插入
		 * 
		 */
		List<JdbcTemplate> jdbcTemplateList = ddbsDaoUtil.getQueryJdbcTemplateList(tableName, null, null);
		
		for(JdbcTemplate j : jdbcTemplateList){
			employeeList.addAll(j.query(sql, new EmployeeWrapper()));
			if(!employeeList.isEmpty())
				break;
		}
		
		return employeeList;
	}
	
}
