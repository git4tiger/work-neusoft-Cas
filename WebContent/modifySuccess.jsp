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
<script type="text/javascript">
	
	function sendRedirect(url){
		window.location.href=url;
	}
	
</script>
</head>

<body >
<table width="1008" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="60">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td width="280" height="60"><img src="images/head_left_top_logo.png" width="280" height="60"></td>
    <td width="300" align="left" valign="middle" class="dddl_head_title">用户信息修改</td>
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
            <td align="left" class="dddl_yh_title">用户信息修改成功</td>
          </tr>
        </table>
          <table width="915" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td height="50"class="dddl_yh_right_data">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您可以通过此账户登录南宁市人社局个人网上办事、南宁招聘系统和南宁市人社综合业务专业技术管理系统</td>
            </tr>
          </table>
          <table width="715"  border="0" cellspacing="0" cellpadding="0" class="dddl_yh_data">
            <tr>
              <td height="35">&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td colspan="2" align="center"><input class="dddl_yh_btn01" id="submit" type="button" onclick="sendRedirect('<%=request.getParameter("service")%>');" value="返回"></td>
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
</body>
</html>
