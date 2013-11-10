package com.len.trans.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class IndexController {
	@RequestMapping(method = RequestMethod.GET, value="/default")
	public String defaultPage(){
		return "default";
	}
	@RequestMapping(method = RequestMethod.GET, value="/menu")
	public String menu(){
		return "menu";
	}
}
