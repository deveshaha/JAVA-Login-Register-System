package com.dam.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dam.db.AccesoDB;
import com.dam.model.Usuario;
import com.dam.view.VLogin;

public class UsuarioPersistencia {
	
	private AccesoDB acceso;
	VLogin vLogin;
	 
	public UsuarioPersistencia() {
		acceso = new AccesoDB();
	}
	
	
	public String consultarPwdPorUser(String usuario) {
		
		String pwd = null;
		
		String query = "SELECT " + UsuarioContract.COL_PWD + " FROM " + UsuarioContract.NOM_TABLA + " WHERE " + UsuarioContract.COL_USUARIO + " =?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		
		try {
			con = acceso.getConnection();
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, usuario);
			
			rslt = pstmt.executeQuery();
			
			if (rslt.next()) {
				pwd = rslt.getString(1);
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				
				if (rslt != null) rslt.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
		return pwd;
	}


	public int registrarUsuario(Usuario usuario) {
		
		int resultado = 0;
	
		String query = "INSERT INTO " + UsuarioContract.NOM_TABLA + " VALUES (?,?)";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = acceso.getConnection();
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, usuario.getUsuario());
			pstmt.setString(2, usuario.getPwd());
			
			resultado = pstmt.executeUpdate();
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				try {
					if (pstmt != null) pstmt.close();
					if (con != null) con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return resultado;
	}
}
