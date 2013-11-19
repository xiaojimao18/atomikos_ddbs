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
	public List<User> getUserList() {
		List<User> userList = new ArrayList<User>();
		String tableName = "user";
		
		List<JdbcTemplate> jdbcTemplateList = ddbsDaoUtil.getQueryJdbcTemplateList(tableName, null, null);
		
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
	
