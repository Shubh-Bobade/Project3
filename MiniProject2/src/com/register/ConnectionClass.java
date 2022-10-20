package com.register;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {
	
	Connection conn = null;
	public Connection getConnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Shubham@2144");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

}
