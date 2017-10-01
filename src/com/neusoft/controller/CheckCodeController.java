package com.neusoft.controller;
/**
 * version 20150817001
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.service.UserService;
import com.neusoft.service.UserServiceImpl;

@SuppressWarnings("serial")
public class CheckCodeController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code");
		String type = request.getParameter("type");
		String phone = request.getParameter("phone");
		String codetype = "";
		if(type.equals("1")){
			codetype = "registeCode";
		}else if(type.equals("2")){
			codetype = "modifyPhoneCode";
		}else if(type.equals("3")){
			codetype = "resetPasswordCode";
		}
		Object obj = request.getSession().getAttribute(codetype);
		String timestamp = "";
		if(null!=obj){
			timestamp = obj.toString();
		}
		String message = "0";
		
		UserService service = new UserServiceImpl(request);
		String sessioncode = service.checkCode(timestamp, phone);
		
		if(code.equals(sessioncode)){
			message = "1";
		}
		response.setHeader("Content-type", "text/html;charset=UTF-8"); 
		response.getWriter().write(message);
	}

}
