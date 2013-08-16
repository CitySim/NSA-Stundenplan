package server.operations.test;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.exceptions.LoginFailedException;
import server.operations.LoginValidator;

/**
 * Test for the login validation.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class LoginValidatorTest extends TestCase {

	private LoginValidator validator;

	@Override
	@Before
	public void setUp() throws Exception {
		this.validator = new LoginValidator();
	}

	@Test
	public void testAccountCreation() {

		final String userName = "markde.2032";
		final String password = "test";

		// TODO : Create user in DB directly

		try {
			LoginValidatorTest.assertTrue(this.validator.validateLoginData(
					userName, password));
		} catch (final LoginFailedException e) {
			LoginValidatorTest.fail();
		}

	}

	@After
	public void cleanUpTestData() {
		// TODO delete user
	}
}
