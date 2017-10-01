package com.neusoft.web.sms;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;


/**

 * @author li-zhch
 * 2013-11-27
 *
 */
public class GetPropertiesTool {

	
	public static GetPropertiesTool getPropertiesTool=null;
	
	private GetPropertiesTool(){
		
	}
	
	public static GetPropertiesTool getGetPropertiesTool() {
		if(getPropertiesTool==null){
			getPropertiesTool=new GetPropertiesTool();
		}
		return getPropertiesTool;
	}
	
	public Properties getProperties(){
		String p_url = "/smscount.properties";
		Properties prop = getProperties(p_url);
		
		return prop;
	}
	public Properties getProperties(String url) {
		Properties prop = new Properties();
		InputStream in = getClass().getResourceAsStream(url);
		try {
			prop.load(in);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return prop;
	}
	
	
}
