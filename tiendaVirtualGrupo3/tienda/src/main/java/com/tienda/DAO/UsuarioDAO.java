package com.tienda.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.tienda.DTO.UsuarioDTO;

public class UsuarioDAO {
	PreparedStatement preparedStatement;
	
	public void registrarUsuario(UsuarioDTO usuario)
	{
		Conexion conex= new Conexion();
		try {
			Statement estatuto = conex.getConnection().createStatement();
			estatuto.executeUpdate("INSERT INTO usuarios VALUES ('"+usuario.getCedulaUsuario()+"','"+usuario.getEmailUsuario()+"','"+usuario.getNombreUsuario()+"','"+usuario.getPassword()+"','"+usuario.getUsuario()+"')");
		JOptionPane.showMessageDialog(null, "se ha registrado Exitosamente", "Informacion", JOptionPane.ERROR_MESSAGE);
		estatuto.close();
		conex.desconectar();
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		JOptionPane.showMessageDialog(null, "No se Registro la persona");
		}
	}
	
	public ArrayList<UsuarioDTO> consultarUsuario(int documento){
		ArrayList<UsuarioDTO> miCliente= new ArrayList<UsuarioDTO>();
		Conexion conex= new Conexion();
		
		try {
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM usuarios WHERE cedula_usuario=?");
			consulta.setInt(1, documento);
			ResultSet res = consulta.executeQuery();
			
			if (res.next()) {
				UsuarioDTO usuario= new UsuarioDTO();
				usuario.setCedulaUsuario(Integer.parseInt(res.getString("cedula_usuario")));
				usuario.setEmailUsuario(res.getString("email_usuario"));
				usuario.setNombreUsuario(res.getString("nombre_usuario"));
				usuario.setPassword(res.getString("password"));
				usuario.setUsuario(res.getString("usuario"));
				
				miCliente.add(usuario);						
			}
			res.close();
			consulta.close();
			conex.desconectar();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "no se pudo consultar la persona\n"+e);
		}
		return miCliente;
	}
	
	public ArrayList<UsuarioDTO> ListarDeUsuarios(){
		ArrayList<UsuarioDTO> miCliente= new ArrayList<UsuarioDTO>();
		Conexion conex= new Conexion();
		
		try {
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM usuarios");
			ResultSet res = consulta.executeQuery();
			
			while (res.next()) {
				UsuarioDTO usuario= new UsuarioDTO();
				usuario.setCedulaUsuario(Integer.parseInt(res.getString("cedula_usuario")));
				usuario.setEmailUsuario(res.getString("email_usuario"));
				usuario.setNombreUsuario(res.getString("nombre_usuario"));
				usuario.setPassword(res.getString("password"));
				usuario.setUsuario(res.getString("usuario"));
				
				miCliente.add(usuario);						
			}
			res.close();
			consulta.close();
			conex.desconectar();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "no se pudo consultar la persona\n"+e);
		}
		return miCliente;
		
	}
	
	public void eliminarUsuario(int cedula) {
		Conexion conex = new Conexion();
		try {
			String query = "DELETE FROM usuarios WHERE cedula_usuario =?";
			preparedStatement = conex.getConnection().prepareStatement(query);
			preparedStatement.setInt(1,cedula);
			preparedStatement.executeUpdate();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	
	
			
	}
}

		
