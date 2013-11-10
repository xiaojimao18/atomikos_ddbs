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
		String tableName  = "tuser";
		//��ʱֻ����ˮƽ��Ƭ��ˮƽ��Ƭ�Ĳ���Ҫ�ҵ�����Ҫ����ı�Ȼ��
		//����Ҫִ�ж����������
		Object[] params={u.getUname(),u.getUpwd()};//ע�������param �� field �ж�Ӧ
		String [] fields = {"uname","upwd"};
		String fields_str = ddbsDaoUtil.getFieldsStr(fields);
		String sql = "insert into tuser("+ fields_str +" ) values(?,?)";//������������ݱ���������׼ȷ
		List <JdbcTemplate> jdbcTemplateList = ddbsDaoUtil.getUpdateJdbcTemplateList(tableName, fields, params);
		
		for(JdbcTemplate j :jdbcTemplateList){
			j.update(sql, params);
		}
		
	}
}
	
