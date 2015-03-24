package com.metability.addressbook;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class AgeCalculator {
	
	private AddressBook addressBook;
	
	public AgeCalculator() throws IOException {
		addressBook = new AddressBook();
	}
	
	public Map<String,String> findOldest() throws ParseException {
	    Comparator<Map.Entry<Integer, Map<String,String>>> byMapValues = new Comparator<Map.Entry<Integer, Map<String,String>>>() {
	        @Override
	        public int compare(Map.Entry<Integer, Map<String,String>> left, Map.Entry<Integer, Map<String,String>> right) {
	            return left.getValue().get("dob").compareTo(right.getValue().get("dob"));
	        }
	    };
	    List<Map.Entry<Integer, Map<String,String>>> addresses = new ArrayList<Map.Entry<Integer, Map<String,String>>>();
	    addresses.addAll(addressBook.entries().entrySet());
	    Collections.sort(addresses, byMapValues);
	    return addresses.get(0).getValue();
	}
    
	public Integer differenceInAgesInDays(String firstName1, String firstName2) throws ParseException {
		Map<String, String> entry1 = addressBook.findByFirstName(firstName1);
		Map<String, String> entry2 = addressBook.findByFirstName(firstName2);
		Date dob1 = new SimpleDateFormat("dd/MM/yy").parse(entry1.get("dob"));
		Date dob2 = new SimpleDateFormat("dd/MM/yy").parse(entry2.get("dob"));
	    return (int) Math.abs(TimeUnit.DAYS.convert(dob1.getTime() - dob2.getTime(), TimeUnit.MILLISECONDS));
	}
}
