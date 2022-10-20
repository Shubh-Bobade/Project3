package com.saurabh.shoppingpage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.register.RegistrationAndLoginForm;
import com.yogesh.adminpage.Cart;

public class Homepage {

	SelectedProduct vpro = new SelectedProduct();

	public void homepage() throws Exception {
		System.out.println("\n-----------------HOMEPAGE--------------");
		System.out.println(" Hello User...");
		System.out.println("	Welcome to the A1_Store ");
		System.out.println("\n 1.Shopping	2.View Orders	3.View Cart  4.Logout ");

		Scanner sc = new Scanner(System.in);
		System.out.println("\n Enter Your Choise ");
		int x = sc.nextInt();

		// switch statement to select method
		switch (x) {

		case 1:
			try {
				System.out.println("--------------------PRODUCTS--------------");
				System.out.println(" \n List of All Products \n");
				shoppingMethod();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case 2:
			System.out.println("You are in View Orders method");
			break;
		case 3:	
			Cart.displayProducts();
			break;
		case 4:
			logout();
			break;

		default:
			System.out.println("please select proper input");
			homepage();
			break;
		}

		sc.close();
	}

	void shoppingMethod() throws Exception {

		Connection c = ConnectionProvider.getConnection();
		String st = "select * from productinfo order by price ";
		PreparedStatement pstmt = c.prepareStatement(st);
		ResultSet rs = pstmt.executeQuery();

		// printing column names on console
		System.out.printf("%-22s%-33s%6s\n", "ProductID", "ProductName", "Price");

		// Condition check
		while (rs.next()) {

			int id = rs.getInt("ProductID");
			String name = rs.getString("ProductName");
			int price = rs.getInt("Price");
			System.out.printf("   %-22d%-30s%6d\n", id, name, price);
		}
		vpro.viewProduct();

	}

	public void viewOrders() {

		System.out.println("you are inside the ViewCart method");

	}

	public void viewCart() {

		System.out.println("You are inside the view cart method");
	}

	public void logout() throws Exception {

		RegistrationAndLoginForm obj = new RegistrationAndLoginForm();
		System.out.println("Program is Terminated...!  Restart The Program");
		System.exit(0);
		//obj.doUserProcess();
	}

}
