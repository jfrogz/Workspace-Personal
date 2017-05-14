<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Insert title here</title>
</head>
<body>
	<h1>postLogin.jsp</h1>
	
	<%
		out.println("Tu usuario es: " + request.getParameter("usuario"));
	%>
	
	<%= "Tu contraseña es: " + request.getParameter("contrasena") %>

</body>
</html>