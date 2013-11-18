package com.len.trans.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.len.trans.dao.UserDao;
import com.len.trans.pojo.User;
import com.len.trans.service.impl.DDBSDaoUtil;

@Repository("userDao")
public class UserDaoImpl implements UserDao{

	@Autowired
	@Qualifier("ddbsDaoUtil")
	private DDBSDaoUtil ddbsDaoUtil;
	
	@Override
	public void add(User u) throws Exception {
		String tableName  = "user";
		//暂时只考虑水平分片，水平分片的插入要找到到底要插入的表，然后
		//可能要执行多条插入操作
		String [] fields = {"UId","UPwd"};
		Object[] params={u.getUserId(),u.getUserPwd()}; //注意这个的param 与 field 有对应
		
		String fields_str = ddbsDaoUtil.getFieldsStr(fields);
		String sql = "insert into user("+ fields_str +" ) values(?,?)";//括号里面的内容必须完整，准确
		List <JdbcTemplate> jdbcTemplateList = ddbsDaoUtil.getUpdateJdbcTemplateList(tableName, fields, params);
		
		for(JdbcTemplate j :jdbcTemplateList){
			j.update(sql, params);
		}
		
	}
}
	
