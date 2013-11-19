package com.len.trans.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.len.trans.pojo.User;
import com.len.trans.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	@Qualifier("userService")
	private UserService userService;
	

	/**
	 * 进入新增
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/_new")
	public ModelAndView _new(HttpServletRequest request,HttpServletResponse response)throws Exception{		
		return new ModelAndView("/user-add");
	}
	
	/**
	 * 新增
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView create(HttpServletRequest request,HttpServletResponse response,User user)throws Exception{
		System.out.println("add***************");
		userService.addUser(user);
		return new ModelAndView("/success","user",user);
	}
	
}
