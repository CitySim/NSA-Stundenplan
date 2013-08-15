package server.operations.test;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import server.operations.AccountCreator;
import server.queries.LoginQuery;

/**
 * Test for the user creation.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class AccountCreatorTest extends TestCase {

	private AccountCreator creator;

	@Override
	@Before
	public void setUp() throws Exception {
		this.creator = new AccountCreator();
	}

	@Test
	public void testAccountCreation() {

		final String name = "Dennis";
		final String familyName = "Markmann";
		final String emailAddress = "test@test.de";

		final String userName = this.creator.createAccount(name, familyName,
				emailAddress);

		AccountCreatorTest
				.assertNotNull(new LoginQuery().getPassword(userName));
	}

	@Test
	public void testPasswordChange() {

		final String name = "Dennis";
		final String familyName = "Markmann";
		final String emailAddress = "test@test.de";

		final String userName = this.creator.createAccount(name, familyName,
				emailAddress);

		final String password = this.creator.changePassword(userName);

		AccountCreatorTest.assertEquals(password,
				new LoginQuery().getPassword(userName));
	}
}