package server.operations;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.entities.Login;
import server.exceptions.DuplicateUserException;
import server.exceptions.EmailAddressException;
import server.exceptions.EmailSendingException;
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
	private String password;

	@Override
	@Before
	public final void setUp() {
		this.handler = new AccountHandler();

	}

	@Test
	public final void testAccountCreation() {
		this.createTestAccount();
		this.password = new LoginQuery().getPassword(this.userName);
		AccountHandlerTest.assertNotNull(this.password);

	}

	@Test
	public final void testPasswordChange() {
		this.createTestAccount();
		try {
			this.password = this.handler.changePassword(this.userName);
		} catch (final EmailSendingException e) {
			fail();
		} catch (final EmailAddressException e) {
			fail();
		}
		AccountHandlerTest.assertTrue(new LoginValidator().validatePassword(this.password, new LoginQuery().getPassword(this.userName)));
	}

	public final void createTestAccount() {

		final String name = "Dennis";
		final String familyName = "Markmann";
		final String eMailAddress = "test@localhost";

		try {
			final Login account = this.handler.createAccount(name, familyName, eMailAddress);
			this.userName = account.getUsername();
		} catch (final EmailSendingException | EmailAddressException e) {
			fail();
		} catch (final DuplicateUserException e) {
		}
	}

	@Test
	@After
	public final void cleanUpTestData() {
		this.handler.deleteAccount(this.userName);
	}
}