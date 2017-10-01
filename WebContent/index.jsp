<%
String para = (String)request.getParameter("service");
%>
<script>
alert('<%=para%>');
</script>
<%
//System.out.println("url==="+request.getQueryString());
para = request.getContextPath()+"/login"+"?service="+para;
response.sendRedirect(response.encodeUrl(para));
if(false){
	if (true) {
		if (para.lastIndexOf("?") > -1) {
			para = para.substring(0, para.length())+"&portal=true";
		} else {
			para = para.substring(0, para.length())+"?portal=true";
		}
		para = request.getContextPath()+"/login"+"?service="+para;
	    //response.sendRedirect(response.encodeUrl(request.getContextPath()+"/login"+"?service="+para.substring(0, para.length()-6)));
	    response.sendRedirect(response.encodeUrl(para));
	} else {
		if (para.lastIndexOf("/") == para.length()-1 || para.lastIndexOf(".do") == para.length()-3) {
			para = para.substring(0, para.length()-1)+"?portal=false";
		} else if (para.lastIndexOf("?") > -1) {
			para = para.substring(0, para.length())+"&portal=false";
		}
		response.sendRedirect(para);
	} 
}
			//response.sendRedirect(response.encodeUrl(request.getContextPath()
		//+ "/"));// + "login?service=" + request.getParameter("service"))
			
%>