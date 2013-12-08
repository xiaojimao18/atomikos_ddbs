package com.len.trans.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.len.trans.pojo.User;
import com.len.trans.service.UserService;

@Controller
public class UserController {

	@Autowired
	@Qualifier("userService")
	private UserService userService;
	

	/**
	 * ��������
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
	 * ����
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView create(HttpServletRequest request,HttpServletResponse response,User user)throws Exception{
		//System.out.println("add***************");
		userService.addUser(user);
		return new ModelAndView("/success","user",user);
	}
	
	@RequestMapping(value="/userSignUp", method=RequestMethod.POST)
	public String UserSignUp(@RequestParam String userId, @RequestParam String userPwd, HttpServletResponse response, HttpServletRequest request){
		HttpSession session = request.getSession(true);
		String location = session.getAttribute("location").toString();
		System.out.println("Sign Up:" + userId + "," + userPwd + "," + location);
		User myUser = new User();
		myUser.setUserId(userId);
		myUser.setUserPwd(userPwd);
		myUser.setLocation(location);
		try {
			userService.addUser(myUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "pphs";
	}
	
	@RequestMapping(value="/UserLogin",method=RequestMethod.POST)
	public String UserLogin(HttpServletRequest request, 
							HttpServletResponse response, 
							@RequestParam String userName, 
							@RequestParam String userPwd){
		HttpSession session = request.getSession(true);
		//�ж��Ƿ�����û�����������ȷ
		String location = session.getAttribute("location").toString();
		System.out.println("Login:" + userName + "," + userPwd);
		try{
			boolean flag = userService.checkUser(userName, userPwd, location);
			System.out.println(flag);
			if(flag == true){
				session.setAttribute("userName", userName);
				return "pphs";
			}else{
				session.setAttribute("userName", null);
				return "error";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
	@RequestMapping(value="/UserLogout")
	public void UserLogout(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		//�ж��Ƿ�����û�����������ȷ
		//�����ȷ
		//session.setAttribute("userName", userName);
		//�������
		session.setAttribute("userName", null);
		
		response.setContentType("text/html; charset=gb2312");

		ServletContext sc = request.getServletContext();

		RequestDispatcher rd = null;

		rd = sc.getRequestDispatcher("/"); //�����ҳ��

		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}