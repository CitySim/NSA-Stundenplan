package server.operations;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import server.exceptions.EmailSendingException;
import server.operations.email.EmailJobHelper;

/**
 * Test for exception email sending.
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
			this.helper.sendCreationMail("test@localhost", "test", "test");
			this.helper.sendPasswordChangeMail("test@localhost", "test", "test");
			this.helper.sendConfirmationMail("test@localhost", "it1a");
		} catch (final EmailSendingException e) {
			EmailSendingTest.fail();
		}
	}
}