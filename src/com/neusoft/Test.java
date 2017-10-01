package com.neusoft;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.neusoft.unieap.config.OrgnizationConfig;
import com.neusoft.unieap.service.orgnization.util.CrytogramUtil;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
//			Properties p = new Properties();  
//			InputStream in = Test.class.getResourceAsStream("/default.properties");
//			p.load(in);  
//			in.close();  
//			if(p.containsKey("css")){  
//			    System.out.println(p.getProperty("css")); ;  
//			}
//			System.out.println(PropertiesUtil.getLogoutIPMapping("http://127.0.0.1:8080/web"));
			System.out.println(testPwd("888888"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

	}
	
	public static String testPwd(String password){
		
		return CrytogramUtil.encrypt(password, OrgnizationConfig.CRYPTOGRAM_ALGORITHM);
	
	}
	
	

}
