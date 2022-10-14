package com.trial;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class HashMapDemo {

	public static void main(String[] args) {

		ArrayList<String> mh = new ArrayList<String>();

		mh.add("pune");
		mh.add("mumbai");
		mh.add("Nagpur");

		ArrayList<String> karnataka = new ArrayList<String>();
		karnataka.add("Banglore");
		karnataka.add("mysore");

		ArrayList<String> madhypradesh = new ArrayList<String>();
		madhypradesh.add("indore");
		madhypradesh.add("bhopal");

		HashMap<String, ArrayList<String>> hmstate = new HashMap<String, ArrayList<String>>();
		hmstate.put("MH", mh); // key is MH and value is complete object
		hmstate.put("KAR", karnataka);
		hmstate.put("MP", madhypradesh);

		HashMap<String, HashMap<String, ArrayList<String>>> hmcountry = new HashMap<String, HashMap<String, ArrayList<String>>>();
		hmcountry.put("India", hmstate);

		Set<String> sethmcountry = hmcountry.keySet();

		for (String str : sethmcountry) {

			System.out.println(str);
			System.out.println(hmcountry.get(str));

		}
		
		System.out.println();

		ArrayList<String> alabama = new ArrayList<String>();
		alabama.add("Alexender city");
		alabama.add("Atmore");

		ArrayList<String> alaska = new ArrayList<String>();
		alaska.add("haines ");
		alaska.add("Nome");

		ArrayList<String> arizona = new ArrayList<String>();
		arizona.add("Globe");
		arizona.add("Ajo");

		HashMap<String, ArrayList<String>> usstate = new HashMap<String, ArrayList<String>>();
		usstate.put("Alba", alabama);
		usstate.put("alk", alaska);
		usstate.put("arz", arizona);

		HashMap<String, HashMap<String, ArrayList<String>>> uscountry = new HashMap<String, HashMap<String, ArrayList<String>>>();
		uscountry.put("United States", usstate);
		
		Set<String> setuscountry = uscountry.keySet();
		
		for(String str : setuscountry) {
			System.out.println(str);
			System.out.println(uscountry.get(str));
			
		}

	}

}
