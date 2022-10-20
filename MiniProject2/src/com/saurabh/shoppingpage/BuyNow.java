package com.saurabh.shoppingpage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BuyNow {

	PreparedStatement ps = null;

	public void buyNow(int temp) throws Exception {
		System.out.println("\n---------------------------------------------\n");
		System.out.println("Here, Your Order Details \n ");
		
		Connection c = ConnectionProvider.getConnection();
		String sql = "select Price, Quantity from addtocart where ProductID = " + temp;
		ps = c.prepareStatement(sql);
		ResultSet rt = ps.executeQuery();

		while (rt.next()) {

			int price = rt.getInt("Price");
			int quantity = rt.getInt("Quantity");

			int totalpr = price * quantity;

			String querry = "update addtocart set totalprice= ? where ProductID = " + temp;

			ps = c.prepareStatement(querry);
			ps.setInt(1, totalpr);
			ps.executeUpdate();
		}

		// after buy now order details
		String st = "select * from addtocart ";
		PreparedStatement pstmt = c.prepareStatement(st);
		ResultSet rs = pstmt.executeQuery();

		// printing column names on console
		System.out.printf("%-22s%-33s%-20s%6s\n", "ProductID", "ProductName", "Quantity", "totalprice");

		// Condition check
		while (rs.next()) {

			int id = rs.getInt("ProductID");
			String name = rs.getString("productName");
			int qty = rs.getInt("Quantity");
			int totalprice = rs.getInt("totalprice");
			System.out.println();
			System.out.printf("   %-22d%-30s%-20d%6d\n", id, name, qty, totalprice);
		}
		nextMethod();
	}
	
	public void nextMethod() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("\nPlease Select the option");
		System.out.println("Press 1.Buy Now");
		System.out.println("Press 2. Add More Products");
		System.out.println("Press 3. Go to Homepage");
		int x = sc.nextInt();
		
		switch (x) {

		case 1:
			PaymentOptions pm = new PaymentOptions();
			pm.paymentOptions();
			break;
		case 2:
			
			break;
		case 3:
			Homepage hm = new Homepage();
			hm.homepage();
			break;

		default:
			System.out.println("Plese enter valid input");
			nextMethod();
			break;
		}
	}



}
