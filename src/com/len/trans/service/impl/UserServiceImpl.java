package com.len.trans.service.impl;

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
	public void add(User u) throws Exception{
		userDao.add(u);
//		throw new Exception("³ö´íÁË£¡£¡£¡");
	}
}
