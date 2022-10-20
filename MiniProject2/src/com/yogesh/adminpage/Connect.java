package com.yogesh.adminpage;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
	
	public static Connection con;
	
	public static Connection createConnection() {
		
		try {
			
			//loading driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//creating connection
			String database_path = "jdbc:mysql://localhost:3306/project";
			String username = "root";
			String password = "Shubham@2144";
			con = DriverManager.getConnection(database_path, username, password);
			
//			con.close();
		} catch(Exception e) {
			
			e.printStackTrace();
		}
		return con;
	}
}
