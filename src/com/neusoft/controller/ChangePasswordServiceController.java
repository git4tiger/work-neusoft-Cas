package com.neusoft.controller;
/**
 * 20160401001
 */
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.service.UserServiceImpl;
import com.neusoft.service.bean.UserAccountBean;

import net.sf.json.JSONObject;

@SuppressWarnings("serial")
public class ChangePasswordServiceController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = "0";
		String desc = "";
		try {
			String username = request.getParameter("account");
			//String oldpassword = request.getParameter("oldpassword");
			String newpassword = request.getParameter("newpassword");
			if(null==username||"".equals(username)){
				desc = "账号参数不能为空";
			}else{
				UserServiceImpl service = new UserServiceImpl(request);
				UserAccountBean b = service.getOneAccountInfo(username);
				if(null==b){
					desc = "无此账号信息";
				}else{
					if(null==newpassword||"".equals(newpassword)){
						desc = "新密码不能为空";
					}else if(checkPassword(newpassword)){
						b.setPassword(newpassword);
						 desc = service.resetPassword(b.getCardid(),b.getPhone(),b.getPassword());
						 if("密码设置成功".equals(desc)){
							 code = "1";
						 }
					}else{
						desc = "新密码长度太长";
					}
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String,String> map = new HashMap<String,String>();
		map.put("code", code);
		map.put("desc", desc);
		JSONObject json = JSONObject.fromObject(map);
		response.setHeader("Content-type", "text/html;charset=UTF-8"); 
		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean checkPassword(String password){
		boolean isSuccess = false;
		if(password.length()<=64){
			isSuccess = true;
		}
		return isSuccess;
	}
	
	

}
