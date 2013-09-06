package server.operations;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for password validation.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class PasswordValidatorTest extends TestCase {

	private LoginValidator validator;
	private PasswordEncryptor encryptor;

	@Override
	@Before
	public final void setUp() {
		this.validator = new LoginValidator();
		this.encryptor = new PasswordEncryptor();
	}

	@Test
	public final void testPasswordValidation() {

		final String password;
		final String hashedPw;

		password = this.encryptor.generatePassword();
		hashedPw = this.encryptor.encryptPassword(password);

		PasswordValidatorTest.assertTrue(this.validator.validatePassword(password, hashedPw));

	}
}