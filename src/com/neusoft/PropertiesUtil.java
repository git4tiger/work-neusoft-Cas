package com.neusoft;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertiesUtil {
	
	//配置文件的路径
    private String configPath=null;
    
    /**
     * 配置文件对象
     */
    private Properties props=null;
    
    /**
     * 默认构造函数，用于sh运行，自动找到classpath下的config.properties。
     */
    public PropertiesUtil() throws IOException{
        InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream("/WEB-INF/cas.properties");
        props = new Properties();
        props.load(in);
        //关闭资源
        in.close();
    }
    
    /**
     * 根据key值读取配置的值
     * Jun 26, 2010 9:15:43 PM
     * @author 朱志杰
     * @param key key值
     * @return key 键对应的值 
     * @throws IOException 
     */
    public String readValue(String key) throws IOException {
        return  props.getProperty(key);
    }
    
    /**
     * 读取properties的全部信息
     * Jun 26, 2010 9:21:01 PM
     * @author 朱志杰
     * @throws FileNotFoundException 配置文件没有找到
     * @throws IOException 关闭资源文件，或者加载配置文件错误
     * 
     */
    public Map<String,String> readAllProperties() throws FileNotFoundException,IOException  {
        //保存所有的键值
        Map<String,String> map=new HashMap<String,String>();
        Enumeration en = props.propertyNames();
        while (en.hasMoreElements()) {
            String key = (String) en.nextElement();
            String Property = props.getProperty(key);
            map.put(key, Property);
        }
        return map;
    }

    
    public static void main(String[] args) {
    	try {
			InputStream inStream = new FileInputStream(new File("../cas.properties")); 
			Properties prop = new Properties();  
			prop.load(inStream);  
			String key = prop.getProperty("db.username");
			System.out.println(key);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * 读取配置文件IP映射地址
     * @param ip
     * @return
     */
    public static String getLogoutIPMapping(String originalUrl){
    	String mappingUrl = "";
    	try {
			Properties p = new Properties();  
			InputStream in = PropertiesUtil.class.getResourceAsStream("/logoutmapping.properties");
			p.load(in);  
			in.close();  
			Set s = p.entrySet();
			Iterator i = s.iterator();
			while(i.hasNext()){
				String value = i.next().toString();
				if(value.indexOf("=")!=-1){
					String pre_ip = value.split("=")[0];
					if(originalUrl.startsWith(pre_ip)){
						mappingUrl = originalUrl.replace(pre_ip, value.split("=")[1]);
						break;
					}else{
						continue;
					}
				}
			}
			if(mappingUrl.equals("")){
				mappingUrl = originalUrl;
			}
		} catch (Exception e) {
			mappingUrl = originalUrl;
			e.printStackTrace();
		}
    	return mappingUrl;
    }
    
    

}
