package com.metability.addressbook;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.ParseException;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

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
		assertEquals(3, addressBook.findByGender("Male").size());
	}

	@Test
	public void findByFirstName() throws IOException, ParseException {
		Map<String, String> entry = addressBook.findByFirstName("Bill");
		assertEquals("Bill McKnight", entry.get("name"));
	}
}
