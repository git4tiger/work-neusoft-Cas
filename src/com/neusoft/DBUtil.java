package com.neusoft;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

public class DBUtil {
	
	private static String dburl;
	private static String username;
	private static String password;
	
	
	public static Connection getConnection(){
		Connection con = null;// 创建一个数据库连接
	        try {
				Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序
				con = DriverManager.getConnection(dburl, username, password);// 获取连接
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return con;
	}
	
	public static void closeAll(Connection con,PreparedStatement pstm,ResultSet rs){
		try {
			if(rs!=null){
				rs.close();
			}
			if(pstm!=null){
				pstm.close();
			}
			if(con!=null){
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void setURL(HttpServletRequest request){
		try {
			Properties propertie = new Properties();  
			String path = request.getSession().getServletContext().getRealPath("/WEB-INF/cas.properties") ;
			FileInputStream inputFile = new FileInputStream(path);
			propertie.load(inputFile);  
            inputFile.close(); 
            username = propertie.get("db.username").toString();
            password = propertie.get("db.password").toString();
            dburl = propertie.get("db.url").toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}

}
