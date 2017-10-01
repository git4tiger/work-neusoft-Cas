package com.neusoft.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.StringTools;
import com.neusoft.service.UserService;
import com.neusoft.service.UserServiceImpl;
import com.neusoft.service.bean.UserAccountBean;

@SuppressWarnings("serial")
public class UserIsExistController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String isExist = "false";
			UserService service = new UserServiceImpl(request);
			String account = request.getParameter("username");
			if (!StringTools.isEmpty(account)) {
				UserAccountBean bean = service.getOneAccountInfo(account.trim());
				if (null != bean) {
					isExist = bean.getPhone();
				}
			}
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.getWriter().write((isExist));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
