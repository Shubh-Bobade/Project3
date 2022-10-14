package com.trial;


import java.util.HashMap;
import java.util.Map;

public class HashMapMethodDemo {
	
	public HashMap<Integer, String> addElement() {
		
		HashMap<Integer,String> hm = new HashMap<Integer,String>();
		hm.put(40, "shubham");
		hm.put(50, "kiran");
		hm.put(60, "aniket");
		
		return hm;
	}

	public static void main(String[] args) {
		
		HashMapMethodDemo hmd = new HashMapMethodDemo();
		System.out.println("calling 1st way "+hmd.addElement());
		
		HashMap<Integer,String> hash = hmd.addElement();
		System.out.println("calling 2nd way "+hash);
		
		Map<Integer,String> map =  hmd.addElement();
		System.out.println("calling 3rd way "+map);
		
		

	}

}
