package com.adminregister;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;
import com.register.ConnectionClass;

public class AdminRegisterForm {

	String id, fullname, username, password, conpass;
	// Scanner sc = null;
	Connection conn = null;
	PreparedStatement ps = null;

	public void insertAdminData(String fullname, String username, String password) {
		ConnectionClass obj = new ConnectionClass();
		conn = obj.getConnection();
		String sql = "insert into admin_register (fullname,username,password)values (?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, fullname);
			ps.setString(2, username);
			ps.setString(3, password);
			int result = ps.executeUpdate();
			System.out.println(" Register Suessfully  " + result);
			ps.close();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void checkPass() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Password again  : ");
		password = sc.next();
		System.out.print("Confirm Password  : ");
		conpass = sc.next();

		if (password.equals(conpass)) {
			System.out.println("Password is matched");
		} else {
			System.out.println("invalid password or password is not matched");
			AdminRegisterForm obj1 = new AdminRegisterForm();
			obj1.checkPass();
		}
	}

	public void doAdminProcess() {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter Full Name : ");
		fullname = sc.nextLine();
		System.out.print("Enter Username : ");
		username = sc.next();
		System.out.print("Enter Password : ");
		password = sc.next();
		System.out.print("Confirm Password : ");
		conpass = sc.next();

		if (password.equals(conpass)) {
			System.out.println("Password is matched");
		} else {
			System.out.println("invalid password or password is not matched");
			AdminRegisterForm obj1 = new AdminRegisterForm();
			obj1.checkPass();
		}

		AdminFields rg = new AdminFields();
		rg.setFullName(fullname);
		rg.setUserName(username);
		rg.setPassword(password);

		AdminRegisterForm obj1 = new AdminRegisterForm();
		obj1.insertAdminData(rg.getFullName(), rg.getUserName(), rg.getPassword());

	}

	public static void main(String[] args) {
		AdminRegisterForm adform = new AdminRegisterForm();
		adform.doAdminProcess();
	}

}
