package com.neusoft.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.service.UserService;
import com.neusoft.service.UserServiceImpl;
import com.neusoft.service.bean.UserAccountBean;

@SuppressWarnings("serial")
public class ModifyAccount extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String account = request.getParameter("account");
		String redirectUrl = request.getParameter("service");
		UserService service = new UserServiceImpl(request);
		UserAccountBean bean = service.getOneAccountInfo(account);
		if(bean==null){
			response.setHeader("Content-type", "text/html;charset=UTF-8"); 
			response.getWriter().write("系统暂未查到此人信息,请稍后再试");
		}else{
			request.setAttribute("user", bean);
			request.setAttribute("redirectUrl", redirectUrl);
			request.getRequestDispatcher("/WEB-INF/user/modifyaccount.jsp").forward(request, response);
		}
	}

}
