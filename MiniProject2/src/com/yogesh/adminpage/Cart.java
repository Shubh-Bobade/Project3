package com.yogesh.adminpage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Cart {
	
	public static void displayProducts() {
		
		Connection con = Connect.createConnection();
		try {
			Statement st = con.createStatement();
			
			String q = "select productid, productname, price from addtocart";
			ResultSet rs = st.executeQuery(q);
			
			System.out.println("---------------------------------------------------------");
			System.out.printf("%-20S%-25S%S%n", "ProductID", "Product Name", "Price");
			System.out.println("---------------------------------------------------------");
			while(rs.next()) {
				
				System.out.printf("%-20d", rs.getInt("productid"));
				System.out.printf("%-25s", rs.getString("productname"));
				System.out.printf("%-25s%n", rs.getInt("Price"));
			}
			
			System.out.println("---------------------------------------------------------");
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static boolean verifyProduct(int id) {
		
		boolean productDisplay = false;
		Connection con = Connect.createConnection();
		
		try {
			Statement st = con.createStatement();
			
			String qr = "select productid from addtocart";
			ResultSet rst = st.executeQuery(qr);
			
			while(rst.next()) {
				
				int cartID = rst.getInt("productid");
				
				if(id == cartID) {
					//Get product name and quantity from addtocart table
					String q = "select productname, quantity from addtocart where productid = "+id;
					ResultSet rs = st.executeQuery(q);
					
					
					while(rs.next()) {
						System.out.printf("\nProduct Name : ");
						System.out.printf(rs.getString("productname"));
						
						System.out.printf("\nQuantity in your Cart : ");
						System.out.printf(rs.getInt("quantity")+"\n");
						
					}
					
					//Get product name and quantity from productinfo table
					String qrs = "select quantity from productinfo where productid = "+id;
					ResultSet rsts = st.executeQuery(qrs);
					
					
					while(rsts.next()) {
						
						System.out.print("Available Quantity : ");
						System.out.printf(rsts.getInt("quantity")+"\n");
					}
					
					
					
					productDisplay = true;
					break;
				}
			}

		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return productDisplay;
	}
	
	public static int showProduct() throws IOException {
		
		int id;
		while(true) {
			
			BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
			//enter id of one of the products above
			System.out.print("\n Enter Product ID For Buy: ");
			id = Integer.parseInt(b.readLine());
			
			//Display product of  the given ID
			boolean productShow = Cart.verifyProduct(id);
			
			if(productShow == true) {
				break;
			}else {
				System.out.println("Enter valid Product ID.");
				continue;
			}
		}
		
		return id;
	}
	
	public static int verifyQuantity(int id, int qty) {
		
		int verifiedtQty = 0;
		Connection con = Connect.createConnection();
		try {
			
			Statement st = con.createStatement();
			
			String q = "select quantity from productinfo where productid = "+id;
			ResultSet rs = st.executeQuery(q);
			
			rs.next();
			int databaseQty = rs.getInt("quantity"); // get quantity from admin table productinfo
			
			while(true) {
				
				if(databaseQty > 0 && databaseQty != 0) {
					
					if(qty > databaseQty) {
						System.out.println("Entered quantity is not available. Only "+
					databaseQty+" products left.");
						break;
					} else if(qty <= databaseQty && qty >= 1) {
//						System.out.println("\nQuantity is available.");
						verifiedtQty = qty;
						break;
					}else if(qty == 0) {
						System.out.println("Please enter valid quantity.");
						break;
					}
				} else {
					
					System.out.println("\nYou cannot buy the product because product is not "
							+ "available.");
					break;
				}
			}

		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return verifiedtQty;
	}
	
	public static int enterQuantity(int id) {
		
		int productQty = 0;
		BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			
			int qty;
			try {
				System.out.print("\nEnter quantity of the product: ");
				qty = Integer.parseInt(b.readLine());
			}catch(Exception e) {
				System.out.println("Please enter valid input.");
				continue;
			}
			
			int enteredQty = Cart.verifyQuantity(id, qty);
			
			if(enteredQty > 0) {
				productQty = enteredQty;
				break;
			} else {

				continue;
			}
		}
		
		return productQty;
	}
	
	public static boolean addQuantityToCart(int enteredQty, int id) {
		
		boolean check = false;
		
		try {
			
			//create connection
			Connection con = Connect.createConnection();

			Statement st = con.createStatement();
			
			//code to get quantity available in the table
			String qr = "select quantity from addtocart where productid = "+ id;
			ResultSet rs = st.executeQuery(qr);
			rs.next();
			
			int availableQty = rs.getInt("quantity");
			int aggregateQty = availableQty + enteredQty;
			
			//Code to check if aggregateQty is greater than databaseQty
			
			//get databaseQty 
			Statement stmt = con.createStatement();
			
			String qrr = "select quantity from productinfo where productid = "+id;
			ResultSet rst = stmt.executeQuery(qrr);
			
			rst.next();
			int databaseQty = rst.getInt("quantity");
			
			if(aggregateQty < databaseQty) {
				
				//get quantity from productId
				//create query
				String q = "update addtocart set Quantity = ? where productId = ?";
				
				//create statement
				PreparedStatement ps = con.prepareStatement(q);
				
				//set values to the parameters
				ps.setInt(1, aggregateQty);
				ps.setInt(2, id);

				
				//execute the query
				ps.executeUpdate();
				
				check = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return check;
	}
	
	public static void removeQuantity(int id) throws NumberFormatException, IOException, SQLException {
		
		Connection con = Connect.createConnection();
		BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			
			try {
				
				System.out.println("Enter quantity to remove: ");
				int qty = Integer.parseInt(b.readLine());
				
				if(qty > 0) {
					
					//Get quantity from addtocart table
					Statement st = con.createStatement();
					String q = "select quantity from addtocart where productid = "+ id;
					ResultSet rs = st.executeQuery(q);
					
					rs.next();
					//quantity of product in the addtocart table
					int cartQty = rs.getInt("quantity");
					
					//remove quantity
					int resultQty = 0;
					if(cartQty > qty) {
						resultQty = cartQty - qty;
					}else if(qty > cartQty) {
						resultQty = qty - cartQty;
					}
					
					//Update the new quantity in addtocart table
					String qr = "update addtocart set quantity = ? where productid = ?";
					PreparedStatement ps = con.prepareStatement(qr);
					
					ps.setInt(1, resultQty);
					ps.setInt(2, id);
					
					ps.executeUpdate();
					break;
				}else {
					System.out.println("Enter quantity greater than zero.");
					continue;
				}
			}catch(Exception e) {
				System.out.println("This is not valid input. Please enter valid input.");
				continue;
			}
		}	
	}
}
