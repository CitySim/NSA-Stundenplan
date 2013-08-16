package server.operations.test;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.operations.NewsLetterValidator;

/**
 * Test for the generation of the newsLetterUrls.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class NewsLetterValidatorTest extends TestCase {
	private NewsLetterValidator validator;

	private String schoolClass;
	private String eMailAddress;

	@Override
	@Before
	public void setUp() throws Exception {
		this.validator = new NewsLetterValidator();
		this.schoolClass = "it1a";
		this.eMailAddress = "test@test.de";
	}

	@Test
	public void testUrlCreation() {

		// TODO Edit url
		final String expectedUrl = "nsa blabla/add_" + this.eMailAddress
				+ "_to:" + this.schoolClass;

		NewsLetterValidatorTest.assertEquals(expectedUrl, this.validator
				.generateRegistrationLink(this.schoolClass, this.eMailAddress));
	}

	@Test
	public void testAddressCreation() {

		boolean success = false;
		this.validator.addAddress(this.eMailAddress, this.schoolClass);

		// TODO get List from DB
		final ArrayList<String> newsLetterList = null;

		for (final String emailAddress : newsLetterList) {
			if (emailAddress.equals(this.eMailAddress)) {
				success = true;
			}
		}
		NewsLetterValidatorTest.assertEquals(true, success);

	}

	@After
	@Test
	public void cleanUpTestData() {
		// TODO delete emailAddress
	}
}
