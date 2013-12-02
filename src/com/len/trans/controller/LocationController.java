package com.len.trans.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LocationController {
	@RequestMapping(value="/locateSH")
	public void locateSH(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		session.setAttribute("location", "上海");
		response.setContentType("text/html; charset=gb2312");

		ServletContext sc = request.getServletContext();

		RequestDispatcher rd = null;

		rd = sc.getRequestDispatcher("/"); //定向的页面

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
	
	@RequestMapping(value="/locateBJ")
	public void locateBJ(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		session.setAttribute("location", "北京");
		response.setContentType("text/html; charset=gb2312");

		ServletContext sc = request.getServletContext();

		RequestDispatcher rd = null;

		rd = sc.getRequestDispatcher("/"); //定向的页面

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
	
	@RequestMapping(value="/locateXG")
	public void locateXG(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		session.setAttribute("location", "香港");
		response.setContentType("text/html; charset=gb2312");

		ServletContext sc = request.getServletContext();

		RequestDispatcher rd = null;

		rd = sc.getRequestDispatcher("/"); //定向的页面

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
