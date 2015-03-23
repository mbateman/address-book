package com.metability.addressbook;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.ParseException;

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
		assertEquals(3, addressBook.findEntriesByGender("Male").size());
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
