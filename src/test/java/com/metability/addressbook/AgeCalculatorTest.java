package com.metability.addressbook;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

public class AgeCalculatorTest {

	AgeCalculator ageCalculator;
	
	@Before
	public void setUp() throws IOException {
		ageCalculator = new AgeCalculator();
	}
	
	@Test
	public void oldestPersonInAddressBook() throws IOException, ParseException {
		assertEquals("Wes Jackson", ageCalculator.findOldest().get("name"));
	}

	@Test
	public void howManyDaysOlderIsBillThanPaul() throws IOException, ParseException {
		int daysOlder = ageCalculator.differenceInAgesInDays("Bill", "Paul");
		assertEquals(2862, daysOlder);
	}
}
