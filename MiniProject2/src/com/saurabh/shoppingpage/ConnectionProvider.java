package com.saurabh.shoppingpage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	
	private static Connection conn;

	public static Connection getConnection() throws SQLException {
		
		String url= "jdbc:mysql://localhost:3306/project";
		String username="root";
		String password="Shubham@2144";
		
		
		try {
				if(conn==null) {
				// loading driver
				Class.forName("com.mysql.cj.jdbc.Driver");				
				conn=(Connection) DriverManager.getConnection(url,username,password);
			    //	System.out.println("connected");
		        }
		} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
			return conn;

   }
}