 <% 
  String redirctURL =  request.getParameter("paraurl");
 System.out.println("redirctURL======="+redirctURL);
 if (redirctURL != null && !"".equals(redirctURL)) {%>
  <script type="text/javascript">
      top.window.location='<%=redirctURL%>';
  </script>
 <%}  else {%>
<jsp:directive.include file="includes/top.jsp" />
		<div id="msg" class="success">
			<h2><spring:message code="screen.logout.header" /></h2>

			<p><spring:message code="screen.logout.success" /></p>
			<p><spring:message code="screen.logout.security" /></p>
			 <input type='button' value='close' onClick="closeWindow()"> 
		</div>
<jsp:directive.include file="includes/bottom.jsp" />
<%}%>