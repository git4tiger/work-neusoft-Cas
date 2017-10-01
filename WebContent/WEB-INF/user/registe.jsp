<!-- version 2150814001 -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>南宁政务单点登录</title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/registe.js"></script>
</head>

<body >
<form id="registeform" action="./user/registeController" method="post">
<table width="1008" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="60">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td width="280" height="60"><img src="images/head_left_top_logo.png" width="280" height="60"></td>
    <td width="300" align="left" valign="middle" class="dddl_head_title">用户注册</td>
    <td align="right" valign="bottom" class="dddl_head_mininav"><a href="#">帐户帮助</a> |  <a href="#">设为首页</a></td>
    <td width="15">&nbsp;</td>
  </tr>
</table>
<table width="1008" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="583" align="center" valign="top" style="background:url(images/login_box_bg.png) left top no-repeat;"><table width="960" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="630" height="75">&nbsp;</td>
        <td width="25">&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td height="500" colspan="3" align="center" valign="top"><table width="915" border="0" cellpadding="0" cellspacing="0" bgcolor="#eff2f5">
          <tr>
            <td width="20" height="45">&nbsp;</td>
            <td align="left" class="dddl_yh_title">用户注册</td>
          </tr>
        </table>
          <table width="915" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td height="50"class="dddl_yh_right_data">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您可以通过注册账户登录南宁市人社局个人网上办事、南宁招聘系统和南宁市人社综合业务专业技术管理系统</td>
            </tr>
          </table>
          <table width="715"  border="0" cellspacing="0" cellpadding="0" class="dddl_yh_data">
            <tr>
             
              <td width="100" height="42" align="right">身份证号：</td>
              <td><input class="dddl_yh_input03" id="username" type="text" maxlength="20" name="username" onblur="checkUser();"></td>
              <td id="username_span" style="color: red;width:150px;"></td>   
            </tr>
            <tr>
              <td height="42" align="right">姓名：</td>
              <td><input class="dddl_yh_input01" type="text" id="name" name="name" maxlength="30" onblur="checkName();"></td>
              <td id="name_span" style="color: red;width:150px;"></td>   
            </tr>
             <tr>
              <td height="42" align="right">手机号码：</td>
              <td><input class="dddl_yh_input04" id="phone" type="text" name="phone" maxlength="15" onblur="checkPhone();"></td>
              <td id="phone_span" style="color: red;width:150px;"></td>  
            </tr>
             <tr>
              <td height="42" align="right">短信验证码：</td>
              <td><input style="width:210px; vertical-align:middle;" maxlength="8" class="dddl_yh_input05" id="code" type="text" name="code" onpropertychange="codeonchange();" onchange="codeonchange();">
                <input class="dddl_yh_button02" id="sendCodeBtn" type="button" onclick="return sendCode(this);" value="免费获取验证码"></td>
                <td id="code_span" style="color: red;width:150px;"></td> 
            </tr>
             <tr>
              <td height="42" align="right">设置密码：</td>
              <td><input class="dddl_yh_input02" id="password" type="password" autocomplete="off" maxlength="20" name="password" onblur="checkPassword();"></td>
              <td id="password_span" style="color: red;width:150px;"></td>     
            </tr>
             <tr>
              <td height="42" align="right">确认密码：</td>
              <td><input class="dddl_yh_input02" id="repassword" type="password" autocomplete="off" maxlength="20" name="repassword" onblur="checkrePassword();"></td>
              <td id="repassword_span" style="color: red;width:150px;"></td>
            </tr>
           
           
            <tr>
              <td height="35">&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td colspan="2" align="center"><input class="dddl_yh_btn01" id="submitBtn" type="button" onclick="submitBtnOnClick();" value="注册"></td>
              </tr>
          </table></td>
        </tr>
    </table></td>
  </tr>
</table>
<table width="1008" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="35" align="center" class="dddl_bottom">COPYRIGHT ©2015 NEUSOFT.COM ALL RIGHT RESERVERD</td>
  </tr>
</table>
<input type="hidden" value="<%=request.getParameter("service") %>" name="rediretUrl"/>
 </form>
 <input type="hidden" value="0" id="hid_username"/>
    <input type="hidden" value="0" id="hid_name"/>
    <input type="hidden" value="0" id="hid_password"/>
    <input type="hidden" value="0" id="hid_repassword"/>
    <input type="hidden" value="0" id="hid_phone"/>
    <input type="hidden" value="0" id="hid_code"/>
    <input type="hidden" value="" id="hid_phone_num"/>
</body>
</html>
