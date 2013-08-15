package server.operations.test;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import server.operations.AccountCreator;
import server.queries.LoginQuery;

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
		final String userName = this.creator.createAccount(name, familyName);

		AccountCreatorTest
				.assertNotNull(new LoginQuery().getPassword(userName));
	}
}