package com.len.trans.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.len.trans.dao.UserDao;
import com.len.trans.pojo.User;
import com.len.trans.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public void addUser(User u) throws Exception{
		userDao.addUser(u);
	}

	@Override
	public boolean checkUser(String userName, String userPwd) {
		List<User> userList = userDao.getUserList();
		for (User user : userList) {
			if (user.getUserId().equalsIgnoreCase(userName)) {
				if (user.getUserPwd().equalsIgnoreCase(userPwd)) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
}
