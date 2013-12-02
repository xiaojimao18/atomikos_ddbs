package com.len.trans.service;

import com.len.trans.pojo.User;

public interface UserService {
	public void addUser(User u) throws Exception;
	public boolean checkUser(String userName, String userPwd, String location);
}
