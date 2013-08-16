package server.operations.test;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.operations.AccountCreator;
import server.queries.LoginQuery;

/**
 * Test for the user creation and password change.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class AccountCreatorTest extends TestCase {

	private AccountCreator creator;
	private String userName;

	@Override
	@Before
	public void setUp() throws Exception {
		this.creator = new AccountCreator();

		final String name = "Dennis";
		final String familyName = "Markmann";
		final String emailAddress = "test@test.de";

		this.userName = this.creator.createAccount(name, familyName,
				emailAddress);
	}

	@Test
	public void testAccountCreation() {

		AccountCreatorTest.assertNotNull(new LoginQuery()
				.getPassword(this.userName));
	}

	@Test
	public void testPasswordChange() {

		final String password = this.creator.changePassword(this.userName);

		AccountCreatorTest.assertEquals(password,
				new LoginQuery().getPassword(this.userName));

	}

	@After
	@Test
	public void cleanUpTestData() {
		// TODO delete user
	}
}