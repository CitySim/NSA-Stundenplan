package server.operations;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.entities.Login;
import server.exceptions.DuplicateUserException;
import server.exceptions.EmailSendingException;
import server.exceptions.LoginFailedException;

/**
 * Test for cookie creation and validation.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class PasswordEcryptorTest extends TestCase {

	private PasswordEncryptor encryptor;
	private LoginValidator validator;

	@Override
	@Before
	public void setUp() throws Exception {
		this.encryptor = new PasswordEncryptor();
		this.validator = new LoginValidator();
	}

	@Test
	public void testCookieCreation() {

		final String name = "Dennis";
		final String familyName = "Markmann";
		final String eMailAddress = "test@test.de";

		Login account = null;
		try {
			account = new AccountHandler().createAccount(name, familyName,
					eMailAddress);
		} catch (final DuplicateUserException e1) {
			e1.printStackTrace();
		} catch (final EmailSendingException e1) {
			e1.printStackTrace();
		}

		final String password = account.getPassword();

		try {
			PasswordEcryptorTest.assertTrue(this.validator.validateLoginData(
					name, password));
		} catch (final LoginFailedException e) {
			this.fail();
		}

		// PasswordEcryptorTest.assertTrue(this.handler.validateCookie(this.cookie));
		// PasswordEcryptorTest.assertTrue(this.handler.deleteCookie(this.cookie));

	}

	@Test
	@After
	public void testCookieDeletion() {
	}
}