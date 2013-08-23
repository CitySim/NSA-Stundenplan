package server.operations;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import server.exceptions.EmailSendingException;
import server.operations.email.EmailJobHelper;

/**
 * Test for exception logging.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class EmailSendingTest extends TestCase {

	private EmailJobHelper helper;

	@Override
	@Before
	public void setUp() {
		this.helper = new EmailJobHelper();
	}

	@Test
	public void testEmailSending() {
		try {
			this.helper.sendPasswordChangeMail("olegscheltow@localhost", "olegscheltow", "oleg");
		} catch (final EmailSendingException e) {
			EmailSendingTest.fail();
		}
	}
}