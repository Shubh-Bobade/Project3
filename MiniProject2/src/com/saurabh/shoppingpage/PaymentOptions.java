package com.saurabh.shoppingpage;

import java.sql.SQLException;
import java.util.Scanner;

import com.register.RegistrationAndLoginForm;

public class PaymentOptions {

	void paymentOptions() throws Exception {
		System.out.println("\n-----------------------------------------------------");
		System.out.println("Please Select the Payment Method\n");
		System.out.println("\nPress 1. for Cash On Delivery");
		System.out.println("Press 2. for Debit Card Payment");
		System.out.println("Press 3. for Credit Card Payment");
		System.out.println("Press 4. for UPI Payment");
		System.out.println("Press 5. Go Back to Homepage");

		Scanner sc = new Scanner(System.in);
		System.out.println("\n please enter your choice \n");
		int x = sc.nextInt();

		// switch statement to select method
		switch (x) {

		case 1:
			
			System.out.println("\nPress 1 for confirm order\n");
			System.out.println("Press 2 for Homepage");
			int no = sc.nextInt();
			if(no == 1) {
				success();
			} else if(no == 2) {
				Homepage hm = new Homepage();
				hm.homepage();
			}
			break;

		case 2:
			System.out.println("Debit card facility is not available only Cash on delivery available\n");
			paymentOptions();
			break;

		case 3:
			System.out.println("Credit card facility is not available only Cash on delivery available\n");
			paymentOptions();
			break;

		case 4:
			System.out.println("UPI facility is not available only Cash on delivery available\n");
			paymentOptions();
			break;

		case 5:
			Homepage hm = new Homepage();
			hm.homepage();
			break;
		default:
			System.out.println("please select proper input");
			paymentOptions();
			break;
		}
	}
	void success() throws Exception {
		System.out.println("\n\n Order Placed Successfully.....!!!\n");
		System.out.println("Thank You For Shopping With Us..!!!\n");
		
		invalidInput();
			
	}
	void invalidInput() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.print("1>. Go to Homepage   2>. LogOut \n");
		System.out.println("--------------------------------------");
		System.out.println("Enter Your Choice");
		int a = sc.nextInt();		
			
		if(a == 1) {
			Homepage hm = new Homepage();
			hm.homepage();
			
		}else if(a == 2) {
			RegistrationAndLoginForm rf = new RegistrationAndLoginForm();
			rf.doUserProcess();
		}else {
			System.out.println("invalid input please Select correct option");
			invalidInput();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
