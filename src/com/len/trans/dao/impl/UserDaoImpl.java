package com.len.trans.dao.impl;

import java.util.ArrayList;
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
	public List<User> getUserList(String location) {
		List<User> userList = new ArrayList<User>();
		String tableName = "user";
		
		String[] field = {"Location"};
		Object[] params = {location};
		List<JdbcTemplate> jdbcTemplateList = ddbsDaoUtil.getQueryJdbcTemplateList(tableName, field, params);
		
		String sql = "select * from " + tableName;
		for(JdbcTemplate j :jdbcTemplateList){
			userList.addAll(j.query(sql, new UserWrapper()));
			if(!userList.isEmpty()) {
				break;
			}
		}

		return userList;
	}
	
	@Override
	public List<User> getUserList() throws Exception {
		List<User> userList = new ArrayList<User>();
		String tableName = "user";
		
		String sql = "select * from " + tableName;
		List<JdbcTemplate> jdbcTemplateList = ddbsDaoUtil.getQueryJdbcTemplateList(tableName, null, null);
		boolean flag = false;
		for(JdbcTemplate j :jdbcTemplateList){
			try{
				userList.addAll(j.query(sql, new UserWrapper()));
			}catch(Exception e){
				e.printStackTrace();
				flag = true;
			}
		}

		if (userList.isEmpty() && flag) throw new Exception("database down in getUserList.");
		return userList;
	}
	
	@Override
	public void addUser(User u) throws Exception {
		String tableName  = "user";
		//��ʱֻ����ˮƽ��Ƭ��ˮƽ��Ƭ�Ĳ���Ҫ�ҵ�����Ҫ����ı�Ȼ��
		//����Ҫִ�ж����������
		String [] fields = {"UId", "UPwd", "Location"};
		Object[] params={u.getUserId(),u.getUserPwd(), u.getLocation()}; //ע�������param �� field �ж�Ӧ
		
		String fields_str = ddbsDaoUtil.getFieldsStr(fields);
		List <JdbcTemplate> jdbcTemplateList = ddbsDaoUtil.getUpdateJdbcTemplateList(tableName, fields, params);
		
		String sql = "insert into user("+ fields_str +" ) values(?,?,?)";//������������ݱ���������׼ȷ
		for(JdbcTemplate j :jdbcTemplateList){
			j.update(sql, params);
		}
	}
}
	
