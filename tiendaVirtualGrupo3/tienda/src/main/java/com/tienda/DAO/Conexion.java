package com.tienda.DAO;

import java.sql.*;

public class Conexion {
	static String bd ="tienda";
	static String login ="root";
	static String password ="JA090183";
	static String url ="jdbc:mysql://localhost/"+bd;
	
	Connection connection = null;
	
	public Conexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url,login,password);
			
			if (connection!=null) {
				System.out.println("Conexion a base de datos" +bd+ "OK\n");
								
			}else {
				System.out.println("No se pudo conectar a la base de datos");				
				
			}
		}catch(SQLException e) {
			System.out.println(e);
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	public Connection getConnection() {
		return connection;
	}
	
	public void desconectar() {
		connection = null;
		
	}
	
	

}
