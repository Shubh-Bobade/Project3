package com.trial;


import java.util.HashMap;

public class HashMapEmployee {

	public static void main(String[] args) {
	
		HashMap<Employee,String> hm = new HashMap<Employee, String>();
		
		Employee emp = new Employee();
		
		Employee emp2 = new Employee();
		emp2.setName("ram");
		
		hm.put(emp, emp.getName());
		hm.put(emp2, emp2.getName());
		
		System.out.println(hm.get(emp));

	}

}
