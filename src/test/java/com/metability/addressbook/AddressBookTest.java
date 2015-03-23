package com.metability.addressbook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class AddressBookTest {

	private AddressBook addressBook;

	@Before
	public void readAddressBook() throws IOException {
		addressBook = new AddressBook();
	}
	
	@Test
	public void countMalesInAddressBook() throws IOException {
		int count = 0;
		for (Integer id: addressBook.entries().keySet()) {
			Map<String, String> entry = addressBook.entries().get(id);
			String gender = entry.get("gender");
			if(gender.equals("Male")) {
				count++;
			}
		}
		assertEquals(3, count);
	}

	@Test
	public void oldestPersonInAddressBook() throws IOException, ParseException {
		assertEquals("Wes Jackson", addressBook.oldest().get("name"));
	}

	@Test
	public void howManyDaysOlderIsBillThanPaul() throws IOException, ParseException {
		Long daysOlder = addressBook.daysOlder("Bill", "Paul");
		assertEquals(2862, daysOlder.intValue());
	}

}
