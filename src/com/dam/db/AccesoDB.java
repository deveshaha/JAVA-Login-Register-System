package com.dam.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccesoDB {
	
	private String driver;
	private String url;
	
	public AccesoDB() {
		driver = "org.sqlite.JDBC";
		url = "jdbc:sqlite:db/ActividadUF10_1.db";
	}
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url);
		
		return con;
	}

}
