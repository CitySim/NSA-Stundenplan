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
	public void setUp() {
		this.validator = new LoginValidator();
		this.handler = new AccountHandler();
	}

	@Test
	public void testPasswordValidation() {

		final String name = "Dennis";
		final String familyName = "Markmann";
		final String eMailAddress = "test@test.de";

		Login account = null;
		try {
			account = this.handler.createAccount(name, familyName, eMailAddress);
		} catch (final EmailSendingException e) {
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
	public void cleanUpTestData() {
		this.handler.deleteAccount(this.userName);
	}
}