<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	String usuario= request.getParameter("usuario");
	String password= request.getParameter("pass");	
	Class.forName("com.mysql.jdbc.Driver");
	Connection miConexion = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda","root","JA090183");
	PreparedStatement ps = miConexion.prepareStatement("SELECT * FROM usuarios WHERE usuario=? AND password = ?");
	ps.setString(1, usuario);
	ps.setString(2, password);
	ResultSet result = ps.executeQuery();
	int i=0;
	while(result.next()){
		i++;
	}
	if(i==1){
		out.println("Usuario autorizado");
		response.sendRedirect("principal.jsp");
	}else{
		out.println("Datos Erroneos");
		response.sendRedirect("Index.html");
		
	}
%>

</body>
</html>