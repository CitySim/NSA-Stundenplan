package server.operations.test;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import server.operations.AccountHandler;
import server.queries.LoginQuery;

/**
 * Test for the user creation and password change.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class AccountHandlerTest extends TestCase {

	private AccountHandler handler;
	private String userName;

	@Override
	@Before
	public void setUp() throws Exception {
		this.handler = new AccountHandler();

		final String name = "Dennis";
		final String familyName = "Markmann";
		final String emailAddress = "test@test.de";

		this.userName = this.handler.createAccount(name, familyName,
				emailAddress);
	}

	@Test
	public void testAccountCreation() {

		AccountHandlerTest.assertNotNull(new LoginQuery()
				.getPassword(this.userName));
	}

	@Test
	public void testPasswordChange() {

		final String password = this.handler.changePassword(this.userName);

		AccountHandlerTest.assertEquals(password,
				new LoginQuery().getPassword(this.userName));

	}

	@Test
	public void cleanUpTestData() {
		this.handler.deleteAccount(this.userName);
	}
}