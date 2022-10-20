package com.register;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.saurabh.shoppingpage.Homepage;
import com.yogesh.adminpage.AdminHomepage;

public class RegistrationAndLoginForm {

	String fullname, mobile, email, address, username, password, conpass;

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	ConnectionClass obj = null;

	public void insertUserData(String fullname, String mobile, String email, String address, String username,
			String password) {

		obj = new ConnectionClass();
		conn = obj.getConnection();
		String sql = "insert into register (fullname,mobile,email,address,username,password)values (?,?,?,?,?,?)";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, fullname);
			ps.setString(2, mobile);
			ps.setString(3, email);
			ps.setString(4, address);
			ps.setString(5, username);
			ps.setString(6, password);
			int result = ps.executeUpdate();
			System.out.println(" Register Suessfully  " + result);

			Homepage hp = new Homepage();
			hp.homepage();

//			ps.close();
//			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// login form process
	public void userLoginProcess(String username, String password) throws Exception {

		obj = new ConnectionClass();
		conn = obj.getConnection();

		try {
			String q = ("Select * from register where username = ? and password = ? ");
			PreparedStatement ps = conn.prepareStatement(q);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();

			if (rs.next()) {
				System.out.println("Login successfully");
				// go to user homepage class
				Homepage hp = new Homepage();
				hp.homepage();
			} else {
				System.out.println("Incorrect username and password");
				forlogin();
			}

			ps.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void adminLoginProcess(String username, String password) throws Exception {

		obj = new ConnectionClass();
		conn = obj.getConnection();

		try {
			String q = ("Select * from admin_register where username = ? and password = ? ");
			PreparedStatement ps = conn.prepareStatement(q);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();

			if (rs.next()) {
				System.out.println("Login successfully");				
				AdminHomepage.homepage();
			} else {
				System.out.println("Incorrect username and password");
				forincorrectadminlogin();
			}
			ps.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void checkPass() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Password again");
		password = sc.next();
		System.out.println("Confirm Password");
		conpass = sc.next();

		if (password.equals(conpass)) {
			System.out.println("\nPassword is matched\n");
		} else {
			System.out.println("invalid password or password is not matched");
			checkPass();
		}
	}

	public void doUserProcess() throws Exception {

		System.out.println("\n* * * * * * *  A1_STORE  * * * * * * * ");
	//	System.out.println();
		System.out.println("--------------------------------------");

		System.out.println("Login Page");
		System.out.println();
		System.out.print(" 1. Sign Up	2. User Login	3. AdminLogin \n");
		
//		System.out.println("Press 0 For Exit ");

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			System.out.println("\nEnter number which operation do you perform");
			int num = Integer.parseInt(bf.readLine());

			if (num < 0 || num > 6) {
				System.out.println("You choose invalid option ");
				doUserProcess();
			} else {
				// for sign up
				if (num == 1) {

					System.out.print("Enter Full Name : ");
					fullname = bf.readLine();

					System.out.print("Enter Mobile NO : ");
					mobile = bf.readLine();

					System.out.print("Enter Email_id : ");
					email = bf.readLine();

					System.out.print("Enter Address : ");
					address = bf.readLine();

					System.out.print("Enter Username : ");
					username = bf.readLine();

					System.out.print("Enter Password : ");
					password = bf.readLine();

					System.out.print("Confirm Password : ");
					conpass = bf.readLine();
//

					if (password.equals(conpass)) {
						System.out.println("\nPassword is matched\n");
					} else {
						System.out.println("invalid password or password is not matched");

						checkPass();
					}

					RegistrationFields rg = new RegistrationFields();
					// rg.setId(id);
					rg.setFullName(fullname);
					rg.setMobNo(mobile);
					rg.setEmail(email);
					rg.setAddress(address);
					rg.setUserName(username);
					rg.setPassword(password);

					insertUserData(rg.getFullName(), rg.getMobNo(), rg.getEmail(), rg.getAddress(), rg.getUserName(),
							rg.getPassword());

					// for user login
				} else if (num == 2) {

					forlogin();

					// for admin login
				} else if (num == 3) {
					forincorrectadminlogin();

				} else if (num == 0) {
					// exit program
					System.out.println("Program terminated.");
					break;
				}
//				else {
//					System.out.println("You choose invalid option ");
//					doUserProcess();
//				}
			}
			break;
		}
	}

	public void forlogin() throws Exception {

		System.out.println("\n- - - - - User Login - - - - - ");
		System.out.println();
		Scanner sc = new Scanner(System.in);
		System.out.print("Username : ");
		String usrname = sc.nextLine();
		System.out.print("Password : ");
		String pass = sc.nextLine();

		userLoginProcess(usrname, pass);
	}

	public void forincorrectadminlogin() throws Exception {
		System.out.println("\n- - - - - Admin Login - - - - - \n");
		Scanner sc = new Scanner(System.in);
		System.out.print("Username : ");
		String usrname = sc.nextLine();

		System.out.println();
		System.out.print("Password : ");
		String pass = sc.nextLine();

		// call adminLoginProcess
		adminLoginProcess(usrname, pass);
	}

	public static void main(String[] args) throws Exception {
		RegistrationAndLoginForm obj = new RegistrationAndLoginForm();
		obj.doUserProcess();
	}

}
