package com.len.trans.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class IndexController {
	@RequestMapping(method = RequestMethod.GET, value="/default")
	public String defaultPage(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		if(session.getAttribute("location") == null)
			session.setAttribute("location", "±±¾©");
		return "pphs";
	}
	@RequestMapping(method = RequestMethod.GET, value="/menu")
	public String menu(){
		return "menu";
	}
}
