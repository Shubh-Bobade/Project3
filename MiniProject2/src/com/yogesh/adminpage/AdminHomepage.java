package com.yogesh.adminpage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.register.RegistrationAndLoginForm;

public class AdminHomepage {

	public static void homepage() throws Exception {
		System.out.println("\n***********ADMIN HOMEPAGE**********\n");
		System.out.println("     Hello, Admin!!!\n");

		BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
		boolean exit = false;

		while (!exit) {

			// Display options to perform operations
			System.out.println(" \n   1.Add Product    2.Remove Product    3.Change Product    4.Show All the Products \n \n"
					+ "         5.Show Users    6.Show the purchase history of User  7.Logout ");
//			System.out.println("\n Operartions :\n");
//			System.out.println("1. Add Product");
//			System.out.println("2. Remove Product");
//			System.out.println("3. Update Product");
//			System.out.println("4. Check All the Products");
//			System.out.println("5. Check Users");
//			System.out.println("6. Check the Purchase History of User");
//			System.out.println("7. Logout");

			try {

				// Get input from Admin and perform operations
				System.out.print("\n Enter serial number of one of the operations above (0 to 6): ");
				int num = Integer.parseInt(b.readLine());

				if (num < 0 || num > 7) {

					System.out.println("Please enter valid number.");
					continue;
				} else {

					if (num == 1) {
						// add product
						System.out.println(" Enter name of product: ");
						String name = b.readLine();

						System.out.println(" Enter price of product: ");
						int price = Integer.parseInt(b.readLine());

						System.out.println(" Enter quantity of product: ");
						int quantity = Integer.parseInt(b.readLine());

						System.out.println(" Enter short description of product: \n");
						String descript = b.readLine();

						Products product = new Products(name, price, quantity, descript);
						boolean result = Operations.addProduct(product);

						if (result == true) {
							System.out.println(" Product successfully added to the database\n");
							System.out.println("-------------------------------------------------------------------------------------");
						} else {

							System.out.println(" Product is not added to the database. Plese try again.");
						}

					} else if (num == 2) {
						// Remove product
						System.out.println(" Enter Product ID to delete the product: ");
						int id = Integer.parseInt(b.readLine());

						boolean result = Operations.deleteProduct(id);

						if (result == true) {
							System.out.println(" Product successfully deleted.");
						} else {
							System.out.println(" Product is not deleted. Please try again.");
						}

					} else if (num == 3) {
						// Update product
						System.out.println(" Enter Name of the product: ");
						String name = b.readLine();

						System.out.println(" Enter ID of the product: ");
						int id = Integer.parseInt(b.readLine());

						System.out.println("Enter Price of the product: ");
						int price = Integer.parseInt(b.readLine());

						System.out.println(" Enter Quantity of the product: ");
						int qty = Integer.parseInt(b.readLine());

						System.out.println(" Enter Description of the product: ");
						String des = b.readLine();

						Products product = new Products(id, name, price, qty, des);
						boolean result = Operations.updateProduct(product);

						if (result == true) {
							System.out.println("Product updated successfully.");
						} else {
							System.out.println("Product not updated. Please try again.");
						}

					} else if (num == 4) {
						// Display all the products
						System.out.println("\n List of all the products is given below:");
						System.out.println();
						Operations.displayProducts();

					} else if (num == 5) {
						// Display the list of users
						System.out.println(" List of all the users is given below:");
						System.out.println();
						Operations.displayUsers();

					} else if (num == 6) {
						// Display the purchase history of a user
						System.out.println(" User's purchase history is given below:");
						System.out.println();
						Operations.userHistory();

					} else if (num == 7) {
						// Exit the program
						RegistrationAndLoginForm rf = new RegistrationAndLoginForm();
						System.out.println(" Program is Terminated...!  Restart The Program");
						System.exit(0);

					}

				}
			} catch (NumberFormatException e) {

				System.out.println("This is not a number. Please try again and enter valid input.");
				continue;
			}
		}
	}

}
