package com.neusoft.controller;
/**
 * version 20160324001
 */
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.neusoft.StringTools;
import com.neusoft.service.UserServiceImpl;
import com.neusoft.service.bean.UserAccountBean;

@SuppressWarnings("serial")
public class UserRegisteServiceController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			 {
		String code = "0";
		String desc = "";
		boolean isSuccess = false;
		try {
			String account = request.getParameter("account");
			String name = request.getParameter("name"); 
			String phone = request.getParameter("phone");
			String password = request.getParameter("password");
			if(!checkParam(account, name, phone, password)){
				desc = "参数异常";
			}else{
				name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
				UserServiceImpl service = new UserServiceImpl(request);
				UserAccountBean b = service.getOneAccountInfo(account);
				boolean isPhoneIsExist = service.checkUserPhone(phone);
				if(null!=b){
					desc = "用户已经存在";
				}else if(isPhoneIsExist){
					desc = "手机号码已经存在";
				}else{
					UserAccountBean bean = new UserAccountBean();
					bean.setCardid(account);
					bean.setId(UUID.randomUUID().toString().replace("-", ""));
					bean.setName(name); //转码
					bean.setPassword(password);//不加密
					bean.setPhone(phone);
					isSuccess = service.doRegiste(bean);
					if(isSuccess){
						code = "1";
						desc = "注册成功";
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	
	public static void main(String[] args) {
		/*try {
			String string = new String("æ¾è³".getBytes("ISO-8859-1"), "UTF-8");
			System.out.println(string);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
		//System.out.println(checkParam("211022198812266117", "12345678901234567890", "18640548328", "11111111"));
		System.out.println(UUID.randomUUID().toString().replace("-", ""));
	}
	
	/**
	 * 入参校验
	 * @param account
	 * @param name
	 * @param phone
	 * @param password
	 * @return
	 */
	public  boolean checkParam(String account,String name,String phone,String password){
		boolean isSuccess = true;
		if(StringTools.isEmpty(account)||StringTools.isEmpty(name)||StringTools.isEmpty(phone)||StringTools.isEmpty(password)){
			return false;
		}
		if(account.length()!=18&&account.length()!=15){
			return false;
		}
		if(!checkCardId(account)){
			return false;
		}
		if(phone.length()!=11){
			return false;
		}
		if(!isNumeric(phone)){
			return false;
		}
		if(password.length()>64){ //modify by jacktong 20160324 长度扩展
			return false;
		}
		if(name.length()>20){
			return false;
		}
		return isSuccess;
	}
	
	public boolean checkCardId(String account){
		String Ai = "";
		if (account.length() == 18) {
            Ai = account.substring(0, 17);
        } else if (account.length() == 15) {
            Ai = account.substring(0, 6) + "19" + account.substring(6, 15);
        }
        if (isNumeric(Ai) == false) {
            return false;
        }
		return true;
	}
	
	 /**
     * 功能：判断字符串是否为数字
     * 
     * @param str
     * @return
     */
    private boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }
}
