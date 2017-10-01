package com.neusoft.controller;
/**
 * version 20150817001
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.StringTools;
import com.neusoft.service.UserService;
import com.neusoft.service.UserServiceImpl;

@SuppressWarnings("serial")
public class ResetPasswordController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response){
		try {
			String phone = request.getParameter("phone");
			String account = request.getParameter("account");
			String password = request.getParameter("password");
			String code = request.getParameter("code");
			String timestamp = request.getParameter("timestamp");
			UserService service = new UserServiceImpl(request);
			//校验 验证码及手机号是否匹配
			//boolean isCheckCode = service.checkCodeForChangePassword(code, phone);
			String sessioncode = service.checkCode(timestamp, phone);
			String result = "";
			if(!StringTools.isEmpty(code)&&!StringTools.isEmpty(sessioncode)){
				if(code.equals(sessioncode)){
					result = service.resetPassword(account, phone,password);
				}
			}else{
				result = "验证码失效，请重新操作";
			}
			response.setHeader("Content-type", "text/html;charset=UTF-8"); 
			response.getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
