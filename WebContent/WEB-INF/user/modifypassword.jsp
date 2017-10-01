<!-- version 20150814001 -->
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
	<script type="text/javascript">
function doResetPassword(){
	checkPassword();
	checkrePassword();
	var password = $("#hid_password").val();
	var repassword = $("#hid_repassword").val();
	if (password == '1' && repassword == '1'){
		$("#submitBtn").disabled = true;
		//ajax 请求
		$.ajax( {
		type : "post",
		url : "./user/resetPassword",
		data : "account=" + $("#username").val()+"&phone="+$("#phone").val()+"&password="+$("#password").val()+"&code="+$("#code").val()+"&timestamp="+$("#timestamp").val(),
		async : false,
		success : function(data) {
			alert(data);
			$("#submitBtn").disabled = false;
			window.location.href="<%=basePath%>/logout?paraurl="+$("#service").val()+"%3Flogout=true";
		}
	});
	}
}

function checkPassword() {
	var password = $("#password").val();
	 var pattern=new RegExp(/^[0-9a-zA-Z]*$/g);
	 //alert(pattern.test(password));
	 
	if (null == $.trim(password) || "" == $.trim(password)) {
		$("#hid_password").val("0");
		document.getElementById("password_span").innerHTML = "密码不能为空";
		return;
	} else if($.trim(password).length<8||$.trim(password).length>20||!pattern.test(password)){
		$("#hid_password").val("0");
		document.getElementById("password_span").innerHTML = "密码为8-20位数字字母组合";
		return;
	}else{
		$("#hid_password").val("1");
		document.getElementById("password_span").innerHTML = "";
	}
}
function checkrePassword() {
	var password = $("#password").val();
	var repassword = $("#repassword").val();
	if (null == $.trim(repassword) || "" == $.trim(repassword)) {
		$("#hid_repassword").val("0");
		//setSubmitBtn();
		document.getElementById("repassword_span").innerHTML = "重复密码不能为空";
		return;
	} else {
		$("#hid_repassword").val("1");
		//setSubmitBtn();
		document.getElementById("repassword_span").innerHTML = "";
	}
	if (password != repassword) {
		$("#hid_repassword").val("0");
		//setSubmitBtn();
		document.getElementById("repassword_span").innerHTML = "两次密码不一致";
	} else {
		$("#hid_repassword").val("1");
		//setSubmitBtn();
		document.getElementById("repassword_span").innerHTML = "";
	}
}

	</script>
</head>

<body >
<form id="form" action="./user/modifyaccountController" method="post">
<table width="1008" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="60">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td width="280" height="60"><img src="images/head_left_top_logo.png" width="280" height="60"></td>
    <td width="300" align="left" valign="middle" class="dddl_head_title">设置密码</td>
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
            <td align="left" class="dddl_yh_title">设置密码</td>
          </tr>
        </table>
          <table width="915" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td height="50"class="dddl_yh_right_data">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在此您可以在此设置您的账户密码</td>
            </tr>
          </table>
          <table border="0" cellspacing="0" cellpadding="0" class="dddl_yh_data">
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
              <td height="70">&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td colspan="2" align="center"><input id="submitBtn" class="dddl_yh_btn01" type="button" onclick="doResetPassword();" value="保存"></td>
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
<input type="hidden" name="id" value="${user.id}"/>
  		<input type="hidden" id="hid_password" value="0"/>
  		<input type="hidden" id="hid_repassword" value="0"/>
  		<input type="hidden" value="${account}" name="account" id="username"/>
  		<input type="hidden" value="${phone}" name="phone" id="phone"/>
  		<input type="hidden" value="${service}" id="service"/>
  		<input type="hidden" value="${code}" name="code" id="code"/>
  		<input type="hidden" value="${timestamp}" name="timestamp" id="timestamp"/>
</form>
</body>
</html>
