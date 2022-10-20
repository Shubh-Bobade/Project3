package com.yogesh.adminpage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;

public class Operations {

	public static boolean addProduct(Products product) {

		boolean productAdded = false;

		try {
			Connection con = Connect.createConnection();
			
			// create query
			String q = "insert into productinfo (ProductName, Price, Quantity, Description) values "
					+ "(?, ?, ?, ?)";

			// create statement
			PreparedStatement ps = con.prepareStatement(q);
			// getting values
			ps.setString(1, product.getProductName());
			ps.setInt(2, product.getPrice());
			ps.setInt(3, product.getQuantity());
			ps.setString(4, product.getDescription());

			// Execute query
			ps.executeUpdate();

			productAdded = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return productAdded;
	}

	public static boolean deleteProduct(int productId) {

		boolean productRemoved = false;
		try {
			Connection con = Connect.createConnection();

			String q = "delete from productinfo where productid = " + productId;
			PreparedStatement ps = con.prepareStatement(q);

			ps.executeUpdate();
			productRemoved = true;
		} catch (Exception e) {

			e.printStackTrace();
		}

		return productRemoved;
	}

	public static boolean updateProduct(Products product) {

		boolean productUpdated = false;

		try {

			// create connection
			Connection con = Connect.createConnection();

			// create query
			String q = "update productinfo set ProductName = ?, Price = ?, Quantity = ?, "
					+ "Description = ? where ProductID = ?";

			// create statement
			PreparedStatement ps = con.prepareStatement(q);

			// set values to the parameters
			ps.setString(1, product.getProductName());
			ps.setInt(2, product.getPrice());
			ps.setInt(3, product.getQuantity());
			ps.setString(4, product.getDescription());
			ps.setInt(5, product.getProductId());

			// execute the query
			ps.executeUpdate();

			// change the value of flag to true
			productUpdated = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return productUpdated;
	}

	public static void displayProducts() {

		try {
			Connection con = Connect.createConnection();

			Statement stmt = con.createStatement();

			String q = "select * from productinfo";
			ResultSet rs = stmt.executeQuery(q);

			System.out.printf("%-20S%-20S%-20S%-20S%-20S%n%n", "Product Name", "ProductID", "Price", "Quantity",
					"Description");

			while (rs.next()) {

				System.out.printf("%-25s", rs.getString(1));
				System.out.printf("%-15d", rs.getInt(2));
				System.out.printf("%-22d", rs.getInt(3));
				System.out.printf("%-18d", rs.getInt(4));
				System.out.printf("%s%n", rs.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void displayUsers() {

		try {
			// connection create
			Connection con = Connect.createConnection();

			Statement stmt = con.createStatement();

			String q = "select * from register";
			ResultSet rs = stmt.executeQuery(q);

			System.out.printf("%-15S%-38S%-23S%-30S%S%n%n", "UserId", "Name", "Contact No.", "Email ID", "Address");

			while (rs.next()) {

				System.out.printf("%-15d", rs.getInt("id"));
				System.out.printf("%-38s", rs.getString("fullname"));
				System.out.printf("%-23s", rs.getString("mobile") + " ");
				System.out.printf("%-30s", rs.getString("email"));
				System.out.printf("%s%n", rs.getString("address"));
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public static void userHistory() {

		try {
			// connection create
			Connection con = Connect.createConnection();

			Statement stmt = con.createStatement();

			String q = "select * from userpurchasehistory";
			ResultSet rs = stmt.executeQuery(q);

			System.out.printf("%-15S%-20S%-20S%S%n%n", "Product Name", "User name", "Quantity", "Date");

			while (rs.next()) {

				System.out.printf("%-15s", rs.getString(1));
				System.out.printf("%-20s", rs.getString(2));
				System.out.printf("%-20d", rs.getInt(3));
				System.out.printf("%s", rs.getString(4));
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
