package server.operations;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.entities.Login;
import server.exceptions.DuplicateUserException;
import server.exceptions.EmailAddressException;
import server.exceptions.EmailSendingException;
import server.exceptions.LoginFailedException;

/**
 * Test for password validation.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class PasswordValidatorTest extends TestCase {

	private LoginValidator validator;
	private AccountHandler handler;
	private String userName;

	@Override
	@Before
	public final void setUp() {
		this.validator = new LoginValidator();
		this.handler = new AccountHandler();
	}

	@Test
	public final void testPasswordValidation() {

		final String name = "Dennis";
		final String familyName = "Markmann";
		final String eMailAddress = "test@localhost";

		Login account = null;
		try {
			account = this.handler.createAccount(name, familyName, eMailAddress);
		} catch (final EmailSendingException e) {
			PasswordValidatorTest.fail();
		} catch (final EmailAddressException e) {
			PasswordValidatorTest.fail();
		} catch (final DuplicateUserException e) {
		}
		final String password = account.getPassword();
		this.userName = account.getUser();

		try {
			PasswordValidatorTest.assertTrue(this.validator.validateLoginData(this.userName, password));
		} catch (final LoginFailedException e) {
			PasswordValidatorTest.fail();
		}

	}

	@Test
	@After
	public final void cleanUpTestData() {
		this.handler.deleteAccount(this.userName);
	}
}