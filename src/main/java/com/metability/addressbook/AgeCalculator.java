package com.metability.addressbook;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class AgeCalculator {
	
	private AddressBook addressBook;
	
	public AgeCalculator() throws IOException {
		addressBook = new AddressBook();
	}
	
	public Map<String,String> findOldest() throws ParseException {
		List<Date> dates = new ArrayList<>();
		for (Integer id : addressBook.entries().keySet()) {
			Map<String, String> entry = addressBook.entries().get(id);
			dates.add(new SimpleDateFormat("dd/MM/yy").parse(entry.get("dob")));			
		}
		Collections.sort(dates);
		Map<String, String> details = new HashMap<>();
		for (Integer id : addressBook.entries().keySet()) {
			Map<String, String> entry = addressBook.entries().get(id);
			if (new SimpleDateFormat("dd/MM/yy").parse(entry.get("dob")).equals(dates.get(0))) {
				details = entry;
				break;
			}
		}
		return details;
	}
	
	public Integer differenceInAgesInDays(String firstName1, String firstName2) throws ParseException {
		Map<String, String> entry1 = addressBook.findByFirstName(firstName1);
		Map<String, String> entry2 = addressBook.findByFirstName(firstName2);
		Date dob1 = new SimpleDateFormat("dd/MM/yy").parse(entry1.get("dob"));
		Date dob2 = new SimpleDateFormat("dd/MM/yy").parse(entry2.get("dob"));
	    return (int) Math.abs(TimeUnit.DAYS.convert(dob1.getTime() - dob2.getTime(), TimeUnit.MILLISECONDS));
	}
}
