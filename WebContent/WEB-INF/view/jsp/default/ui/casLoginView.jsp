<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>南宁政务单点登录</title>
<link href="./css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
<!--
function MM_swapImgRestore() { //v3.0
  document.getElementById('btnImg').src="<%= request.getContextPath()%>/css/new_images/button.gif";
  document.getElementById('resetbtnImg').src="<%= request.getContextPath()%>/css/new_images/reset_button.gif";
}
function MM_swapImage(elem, imgSrc) { //v3.0
	elem.src=imgSrc;
}

function fetchJcap(){
 	document.getElementById("jcaptcha").src="<%= request.getContextPath()%>/jcaptcha/"+ Math.round(Math.random(100)*100000);
}

/**
*回车提交操作
*/
function enterToSubmit(evt){
  if (evt.keyCode == 13){
    submit_form();
  }
}


/**
*提交表单
*/
var sumbitted = false;
function submit_form(){
  if (!sumbitted){
  	  document.getElementById("logonform").submit();
  	  document.getElementById("loginButton").disabled=true;
	  sumbitted = true;
  }
}

/**
*修改通用的回车换Tab处理方式
*/
function enterToTab(evt,target){
	if(evt.keyCode != 13){
		return;
	}
	target = document.getElementById(target);
	target && target.focus();
}

function resetBt(){
    document.getElementById("username").value = "";
    document.getElementById("password").value = "";
    document.getElementById("checkCode").value = "";
}

function goRegiste(){
	window.location.href="./user/registe?service="+encodeURIComponent('<%=request.getParameter("service")%>');
	
}

function goResetPassword(){ 
	window.location.href="./forgetpassword.jsp?service="+encodeURIComponent('<%=request.getParameter("service")%>');
}

//-->
</script>
</head>

<body >
<form:form method="post" id="logonform"   commandName="${commandName}" htmlEscape="true" >
<table width="1008" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="60">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td width="280" height="60"><img src="images/head_left_top_logo.png" width="280" height="60"></td>
    <td width="300" align="left" valign="middle" class="dddl_head_title">用户登录</td>
    <td align="right" valign="bottom" class="dddl_head_mininav"><a href="#">帐户帮助</a> |  <a href="#">设为首页</a></td>
    <td width="15">&nbsp;</td>
  </tr>
</table>
<table width="1008" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="583" align="center" valign="top" style="background:url(images/login_box_bg.png) left top no-repeat;"><table width="960" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="75">&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td width="630" align="center" valign="top"><table width="630" border="0" cellpadding="0" cellspacing="0" bgcolor="#eff2f5">
          <tr>
            <td width="20" height="45">&nbsp;</td>
            <td align="left" class="dddl_yh_title">用户登录</td>
          </tr>
        </table>
          <table width="100" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td height="50">&nbsp;</td>
            </tr>
          </table>
          <table width="560" border="0" cellspacing="0" cellpadding="0" class="dddl_yh_data">
            <tr>
             
              <td height="30" align="left">身份证号：</td>
              <td width="169">&nbsp;</td>
            </tr>
            <tr>
              <td  height="36" align="left"><input class="dddl_yh_input01"  tabIndex="1"  type="text" id="username" name="username" maxlength="20" size="20" onkeydown="enterToTab(event,'password')"></td>
              <td >&nbsp;</td>
            </tr>
            <tr>
              <td height="20" align="left">&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td  height="30" align="left">密码：</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td height="36" align="left"><input class="dddl_yh_input02" tabIndex="2" type="password" size="20" id="password" name="password" maxlength="20" onkeydown="enterToTab(event,'jcaptcha_response')"></td>
              <td height="36" align="center" style="color:red;font-size: smaller;"><form:errors path="*"  id="status11" element="span"/></td>
            </tr>
            <tr>
              <td height="45" align="right" valign="middle">
              <input class="dddl_yh_button01" type="button" onclick="goResetPassword();" name="button" id="button" value="忘记密码？"></td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td height="50">&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td align="left"><input class="dddl_yh_btn01" type="submit"  name="loginButton" id="loginButton" onkeydown="enterToSubmit(event)"  onclick="submit_form()" value="登录"></td>
              <td>&nbsp;</td>
            </tr>
          </table></td>
        <td width="25" height="500" style="background:url(images/login_yh_fenge.png) center -30px no-repeat;">&nbsp;</td>
        <td align="center" valign="top"><table width="290" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="60" align="center" valign="top" class="dddl_yh_right" >创建系统新用户</td>
          </tr>
          <tr>
            <td height="150" valign="top" class="dddl_yh_right_data">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;创建新用户后，您可以通过系统所属该账户登录南宁市人社局个人网上办事、南宁招聘系统和南宁市人社综合业务专业技术管理系统</td>
          </tr>
          <tr>
            <td><input class="dddl_yh_btn02" type="button" name="button3" id="button3" onclick="goRegiste();" value="创建用户"></td>
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
					 <input type="hidden" name="lt" value="${flowExecutionKey}" />
					 <input type="hidden" name="_eventId" value="submit" />
					 <input type="hidden" name="captchaId" value="<%=request.getSession().getId()%>" />
</form:form>
</body>
</html>

