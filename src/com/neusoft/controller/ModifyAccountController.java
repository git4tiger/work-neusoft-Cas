package com.neusoft.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.neethi.Assertion;

import sun.misc.BASE64Encoder;

import com.neusoft.service.UserService;
import com.neusoft.service.UserServiceImpl;
import com.neusoft.service.bean.UserAccountBean;

@SuppressWarnings("serial")
public class ModifyAccountController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			UserService service = new UserServiceImpl(request);
			String id = request.getParameter("id");
			String account = request.getParameter("account");
			String phone = request.getParameter("phone");
			String redirectUrl = request.getParameter("redirectUrl");
			UserAccountBean bean = new UserAccountBean();
			bean.setId(id);
			bean.setCardid(account);
			bean.setPhone(phone);
			//bean.setPassword(EncodePasswd(password));
			boolean isSuccess = service.modifyAccount(bean);
			
			String redirecturl = "/modifySuccess.jsp?service="+redirectUrl;
			if(!isSuccess){
				redirecturl = "/modifyerror.jsp?service="+redirectUrl;
			}
			request.getSession().removeAttribute("modifyPhoneCode"); //修改账户信息后，清空验证码
			response.sendRedirect(request.getContextPath()+redirecturl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public String EncodePasswd(String varCode)
	  {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		messageDigest.reset(); 
		byte[] bytes = varCode.getBytes();
		byte[] out = messageDigest.digest(bytes);
		BASE64Encoder enc = new BASE64Encoder();
	    return enc.encode(out);
	  }
	
}
