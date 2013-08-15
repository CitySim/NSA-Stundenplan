package server.operations.test;

import junit.framework.TestCase;

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

	@Override
	@Before
	public void setUp() throws Exception {
		this.validator = new NewsLetterValidator();
	}

	@Test
	public void testAccountCreation() {

		final String schoolClass = "it1a";
		final String eMailAddress = "test@test.de";

		// TODO Edit url
		final String expectedUrl = "nsa blabla/add_" + eMailAddress + "_to:"
				+ schoolClass;

		LoginValidatorTest.assertEquals(expectedUrl, this.validator
				.generateRegistrationLink(schoolClass, eMailAddress));

	}
}
