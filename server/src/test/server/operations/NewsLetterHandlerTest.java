package server.operations;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for the generation of the newsLetterUrls.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class NewsLetterHandlerTest extends TestCase {

	private NewsLetterHandler handler;

	private String schoolClass;
	private String eMailAddress;
	private String url;

	@Override
	@Before
	public void setUp() throws Exception {
		this.handler = new NewsLetterHandler();
		this.schoolClass = "it1a";
		this.eMailAddress = "test@test.de";
	}

	@Test
	public void testUrlCreation() {

		// TODO Edit url
		this.url = "nsa blabla/add_" + this.eMailAddress + "_to:"
				+ this.schoolClass;

		NewsLetterHandlerTest.assertEquals(this.url, this.handler
				.generateRegistrationLink(this.schoolClass, this.eMailAddress));

	}

	// @Test
	// public void testAddressCreation() {
	//
	// this.testUrlCreation();
	//
	// boolean success = false;
	//
	// NewsLetterHandlerTest.assertTrue(this.handler
	// .validateConfirmation(this.url));
	//
	// final List<Newsletter> newsLetterList = new NewsletterQuery()
	// .getAllNewsletters();
	//
	// for (final Newsletter newsLetter : newsLetterList) {
	// if (newsLetter.getEmail().getEMailAddress()
	// .equals(this.eMailAddress)) {
	// success = true;
	// }
	// }
	// NewsLetterHandlerTest.assertEquals(true, success);
	//
	// NewsLetterHandlerTest.assertTrue(this.handler.removeAddress(
	// this.eMailAddress, this.schoolClass));
	// }
}
