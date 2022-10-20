package com.saurabh.shoppingpage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.yogesh.adminpage.AddToCartPage;

public class SelectedProduct {

	Scanner scan = new Scanner(System.in);

	void viewProduct() throws Exception {

		Connection c = ConnectionProvider.getConnection();
		System.out.println("\n please select the product id to buy the item ");
		int id = scan.nextInt();
		String st = "SELECT * FROM productinfo WHERE ProductID =  " + id;
		PreparedStatement pstmt = c.prepareStatement(st);
		ResultSet rs = pstmt.executeQuery();
		System.out.println("\n------------VIEW PRODUCT---------------");
		System.out.println("\n Your Product Details... \n");

		while (rs.next()) {
			int pid = rs.getInt("ProductID");
			String name = rs.getString("ProductName");
			int price = rs.getInt("Price");
			String description = rs.getString("Description");

			System.out.printf("Product id  : " + pid + "\n" + "Product name  : " + name + "\n" + "The product price  : "
					+ "Rs." + price + "\n" + "Product Descrition  : " + description);
			
			System.out.println("\n------------------------------------------------------------------------------------");
			System.out.println("\n 1.Add to Cart	2.Buy Now	3.Go back to view All Product	4.Go to Homepage \n");

			System.out.println(" Please select one of the option above");
			int num = scan.nextInt();
			// switch statement to select method

			switch (num) {

			case 1:

				String sql = "insert into addtocart (ProductID,productName,Price)values (?,?,?)";
				PreparedStatement pst = c.prepareStatement(sql);
				pst.setLong(1, pid);
				pst.setString(2, name);
				pst.setLong(3, price);

				pst.executeUpdate();

				System.out.println("\nProcuct is Added....  ");

				AddToCartPage.addToCart();
				break;

			case 2:
				System.out.println("buy now");

				break;

			case 3:
				Homepage hm = new Homepage();
				hm.shoppingMethod();
				break;

			case 4:
				Homepage hm1 = new Homepage();
				hm1.homepage();
				break;

			default:
				System.out.println("please select proper input");
				break;
			}
		}
	}

}
