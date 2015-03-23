package com.metability.addressbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

public class AddressBook {

    private Map<Integer, Map<String,String>> entries = new HashMap<>();

	public AddressBook() throws IOException {
		enterEntries();
	}

	private void enterEntries() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("resources/AddressBook")));
        String line;
        int id = 1;
        while ((line = reader.readLine()) != null) {
        	String[] split = line.split(",");
        	Map<String, String> details = new HashMap<>();
        	details.put("name", split[0].trim());
        	details.put("gender", split[1].trim());
        	details.put("dob", split[2].trim());
        	entries.put(id++, details);
        }
        reader.close();
	}
	
	public Map<Integer, Map<String,String>> entries() {
		return entries;
	}
	
	public Map<String,String> oldest() throws ParseException {
		List<Date> dates = new ArrayList<>();
		for (Integer id : entries().keySet()) {
			Map<String, String> entry = entries.get(id);
			dates.add(new SimpleDateFormat("dd/MM/yy").parse(entry.get("dob")));			
		}
		Collections.sort(dates);
		Map<String, String> details = new HashMap<>();
		for (Integer id : entries().keySet()) {
			Map<String, String> entry = entries.get(id);
			if (new SimpleDateFormat("dd/MM/yy").parse(entry.get("dob")).equals(dates.get(0))) {
				details = entry;
				break;
			}
		}
		return details;
	}

	private Map<String, String> findEntryByFirstName(String name) {
		Map<String, String> details = new HashMap<>();
		for (Integer id : entries().keySet()) {
			Map<String, String> entry = entries.get(id);
			if (entry.get("name").startsWith(name)) {
				details = entry;
			}
		}
		return details;
	}

	public Long daysOlder(String firstName1, String firstName2) throws ParseException {
		Map<String, String> entry1 = findEntryByFirstName(firstName1);
		Map<String, String> entry2 = findEntryByFirstName(firstName2);
		Date dob1 = new SimpleDateFormat("dd/MM/yy").parse(entry1.get("dob"));
		Date dob2 = new SimpleDateFormat("dd/MM/yy").parse(entry2.get("dob"));
	    return Math.abs(TimeUnit.DAYS.convert(dob1.getTime() - dob2.getTime(), TimeUnit.MILLISECONDS));
	}
	
	
}
