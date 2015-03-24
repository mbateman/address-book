package com.metability.addressbook;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class AgeCalculator {
	
	private AddressBook addressBook;
	
	public AgeCalculator() throws IOException {
		addressBook = new AddressBook();
	}
	
	public Map<String,String> findOldest() throws ParseException {
	    Comparator<Map.Entry<Integer, Map<String,String>>> byValue = (entry1, entry2) -> entry1.getValue().get("dob").compareTo(
	            entry2.getValue().get("dob"));
	    Optional<Map.Entry<Integer, Map<String,String>>> entry = addressBook.entries()
	            .entrySet()
	            .stream()
	            .sorted(byValue)
	            .findFirst();
	    
	    return entry.get().getValue();
	}
    
	public Integer differenceInAgesInDays(String firstName1, String firstName2) throws ParseException {
		Map<String, String> entry1 = addressBook.findByFirstName(firstName1);
		Map<String, String> entry2 = addressBook.findByFirstName(firstName2);
		Date dob1 = new SimpleDateFormat("dd/MM/yy").parse(entry1.get("dob"));
		Date dob2 = new SimpleDateFormat("dd/MM/yy").parse(entry2.get("dob"));
	    return (int) Math.abs(TimeUnit.DAYS.convert(dob1.getTime() - dob2.getTime(), TimeUnit.MILLISECONDS));
	}
}
