<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>测试手机端APP注册接口</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <center>
    <form action="./user/userChangePasswordService" method="post">
    	<table border='1' cellpadding="1" cellspacing="0" width="30%">
    		<caption>测试修改密码</caption>
    		<tr>
    			<td>身份证号</td>
    			<td><input type="text" name="account"/></td>
    		</tr>
    		<tr>
    			<td>旧密码</td>
    			<td><input type="text" name="oldpassword"/></td>
    		</tr>
    		<tr>
    			<td>密码</td>
    			<td><input type="text" name="newpassword"/></td>
    		</tr>
    		<tr>
    			<td><input type="submit" value="提交"/></td>
    		</tr>
    	</table>
    </form>
    </center>
  </body>
</html>
