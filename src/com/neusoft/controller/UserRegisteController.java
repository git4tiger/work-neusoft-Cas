package com.neusoft.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.xwork.StringUtils;

import sun.misc.BASE64Encoder;

import com.neusoft.service.UserService;
import com.neusoft.service.UserServiceImpl;
import com.neusoft.service.bean.UserAccountBean;

@SuppressWarnings("serial")
public class UserRegisteController extends HttpServlet {


	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response){
		try {
			boolean flag = true;
			UserService service = new UserServiceImpl(request);
			String username = request.getParameter("username");
			String name = new String( request.getParameter("name").getBytes("ISO-8859-1"),"UTF-8") ;
			String password = request.getParameter("password");
			String phone = request.getParameter("phone");
			String redirectUrl = request.getParameter("rediretUrl");
			if(StringUtils.isEmpty(username)||StringUtils.isEmpty(name)||StringUtils.isEmpty(password)||StringUtils.isEmpty(phone)){
				flag = false;
			}
			if(flag){
				UserAccountBean account = new UserAccountBean();
				account.setCardid(username);
				account.setId(UUID.randomUUID().toString().replace("-", "")); 
				account.setName(name);
				account.setPhone(phone);
				account.setPassword(EncodePasswd(password));
				boolean isSuccess = service.doRegiste(account);
				if(isSuccess){
					request.getSession().removeAttribute("registeCode"); //注册成功后清空验证码
					response.sendRedirect(request.getContextPath()+"/registesuccess.jsp?service="+java.net.URLEncoder.encode(redirectUrl));
				}
			}else{
				response.sendRedirect(request.getContextPath()+"/WEB-INF/user/registe.jsp?service="+java.net.URLEncoder.encode(redirectUrl));
			}
			
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
