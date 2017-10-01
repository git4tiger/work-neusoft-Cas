package com.neusoft.controller;
/**
 * version 20150819001
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.neusoft.service.UserServiceImpl;
import com.neusoft.util.DESBase64Util;

@SuppressWarnings("serial")
public class UserServiceController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserServiceImpl service = new UserServiceImpl(request);
		String account = request.getParameter("account");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		String result = service.getAccountInfo(account);
		if(!result.equals("")){
			result = DESBase64Util.encodeInfo(result);
		}
		response.getWriter().write(result);
	}

}
