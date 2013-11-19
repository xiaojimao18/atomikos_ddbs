package com.len.trans.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.len.trans.pojo.User;

public class UserWrapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		
		user.setUserId(rs.getString("UId"));
		user.setUserPwd(rs.getString("UPwd"));
		
		return user;
	}

}
