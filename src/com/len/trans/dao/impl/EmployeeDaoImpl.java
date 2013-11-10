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
		 * ��������ܲ���
		 * �ܲ������Ŷ��еĹ�Ա
		 * ���ǿ����ܲ����ݿ�ҵ�
		 * �ͷ��ʸ���վ�������
		 * ������޸ĵĻ���Ҫ ά�����ݵ�һ����
		 * 
		 * ���ʲ���
		 * �����Լ����ص����ݿ⣬������صĽ��Ϊ�գ������е���վ�㣬ȥ����
		 * 
		 * ������ԣ���վ��û�в���
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
