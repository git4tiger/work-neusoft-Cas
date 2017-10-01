package com.neusoft.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.service.UserService;
import com.neusoft.service.UserServiceImpl;

@SuppressWarnings("serial")
public class CheckAccountAndPhoneController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String account = request.getParameter("account");
			String phone = request.getParameter("phone");
			UserService service = new UserServiceImpl(request);
			boolean isExist = service.checkAccountAndPhone(account, phone);
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.getWriter().write(isExist+"");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
