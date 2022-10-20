package com.yogesh.adminpage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.saurabh.shoppingpage.BuyNow;

public class AddToCartPage {
	//static int temp;

	public static void addToCart() throws Exception {

		// System.out.println("Products Added To The Cart:\n");

		// Method to display only the name of the products present in addtocart table
		// Products will display according to their ID and ID will also diplay
		Cart.displayProducts();

		BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
		int id = Cart.showProduct();
		
		// Code to enter quantity
		System.out.println("\nDo you want to add quantity? (y/n)");
		while (true) {

			try {
				char response = (char) b.read();
				if (response == 'y') {

					while (true) {
						try {
							int insertQty = Cart.enterQuantity(id);

							boolean addedToCart = Cart.addQuantityToCart(insertQty, id);
							if (addedToCart == true) {
								System.out.println("Quantity added.");
								break;
							} else if (addedToCart == false) {
								System.out.println("Quantity not added. ");
								continue;
							}

						} catch (Exception e) {
							System.out.println("\nThis is not valid input.");
							continue;
						}
					}
					break;
				} else if (response == 'n') {
					break;
				}
			} catch (Exception e) {
				System.out.println("\nThis is not valid input. Please enter valid input.");
				continue;
			}
		}

		// Code to remove quantity
		System.out.println("Do you want to remove quantity? (y/n)");

		while (true) {
			Connection con = Connect.createConnection();
			PreparedStatement ps = null;
//			try {

				char response = (char) b.read();
				if (response == 'y') {
					
					Cart.removeQuantity(id);
					System.out.println("Quantity of product removed successfully.");
					 

					// System.out.println(id);
					Statement st = con.createStatement();
					String q = "select productname, quantity from addtocart where productid = " + id;
					ResultSet rs = st.executeQuery(q);

					while (rs.next()) {
						System.out.print("\nProduct Name: ");
						System.out.printf(rs.getString("productname"));

						System.out.print("\nQuantity in your Cart: ");
						System.out.printf(rs.getInt("quantity") + "\n");
						break;
					}

					BuyNow bn = new BuyNow();
					bn.buyNow(id);

				} else if (response == 'n') {
					
					BuyNow bn = new BuyNow();
					bn.buyNow(id);
					break;
				}

//			} catch (Exception e) {
		//		
				continue;
//			}

		}
	}

	static void afterQtyRemove(int id) {

	}

//	public static void main(String args[]) {
//		
//		try {
//			//This method throws exception so write it in try block or throw exception in the method
//			AddToCartPage.addToCart();
//		}catch(Exception e) {
//			
//			e.printStackTrace();
//		}

}
