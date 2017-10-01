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
public class ResetPassword extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String account = request.getParameter("account");
		String phone = request.getParameter("phone");
		String code = request.getParameter("code"); 
		UserService service = new UserServiceImpl(request);
		String redirectUrl = request.getParameter("service");
		boolean ischecked = service.checkAccountAndPhone(account, phone);
		if(ischecked){
			request.setAttribute("account", account);
			request.setAttribute("phone", phone);
			request.setAttribute("service", redirectUrl);
			request.setAttribute("code", code);
			request.setAttribute("timestamp", request.getSession().getAttribute("resetPasswordCode"));
			request.getSession().removeAttribute("resetPasswordCode"); //修改密码，点击下一步后，清空验证码
			request.getRequestDispatcher("/WEB-INF/user/modifypassword.jsp").forward(request, response);
		}else{
			response.sendRedirect("/WEB-INF/forgetpassword.jsp?service="+redirectUrl);
		}
	}

}
