package com.saurabh.shoppingpage;

import java.sql.SQLException;



public class Test {

	public static void main(String[] args) throws SQLException {
	
		Homepage hmp = new Homepage();
		
		ConnectionProvider.getConnection();
		
		try {
			hmp.homepage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}

	
	

		