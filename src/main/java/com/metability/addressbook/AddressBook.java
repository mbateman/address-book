package com.metability.addressbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AddressBook {

    private Map<Integer, Map<String,String>> entries = new HashMap<>();

	public AddressBook() throws IOException {
		enterEntries();
	}

	public Map<Integer, Map<String,String>> entries() {
		return entries;
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
	
	public Map<String, String> findByFirstName(String name) {
		Map<String, String> details = new HashMap<>();
		for (Integer id : entries().keySet()) {
			Map<String, String> entry = entries.get(id);
			if (entry.get("name").startsWith(name)) {
				details = entry;
			}
		}
		return details;
	}
	
	public Map<Integer, Map<String,String>> findByGender(String gender) {
		Map<Integer, Map<String,String>> details = new HashMap<>();
		for (Integer id: entries().keySet()) {
			Map<String, String> entry = entries().get(id);
			if(entry.get("gender").equals(gender)) {
				details.put(id, entry);
			}
		}		
		return details;
	} 
	
}
