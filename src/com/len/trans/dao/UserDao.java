package com.len.trans.dao;

import java.util.List;

import com.len.trans.pojo.User;

public interface UserDao {
	public void addUser(User u) throws Exception;
	public List<User> getUserList();
}
