package server.operations.test;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import server.operations.LoginValidator;

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

		LoginValidatorTest.assertTrue(this.validator.validateLoginData(
				userName, password));

	}
}
