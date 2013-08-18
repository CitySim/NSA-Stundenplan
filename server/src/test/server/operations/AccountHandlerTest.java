package server.operations;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.entities.Login;
import server.exceptions.DuplicateUserException;
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
    public void setUp() throws EmailSendingException, DuplicateUserException {
        this.handler = new AccountHandler();

        final String name = "Dennis";
        final String familyName = "Markmann";
        final String eMailAddress = "test@test.de";
        try {
            final Login account = this.handler.createAccount(name, familyName, eMailAddress);
            this.userName = account.getUser();
        } catch (final EmailSendingException | DuplicateUserException e) {
        }
    }

    @Test
    public void testAccountCreation() throws EmailSendingException {

        this.password = new LoginQuery().getPassword(this.userName);
        AccountHandlerTest.assertNotNull(this.password);

    }

    @Test
    public void testPasswordChange() throws EmailSendingException {

        try {
            this.password = this.handler.changePassword(this.userName);
        } catch (final EmailSendingException e) {
        }

        AccountHandlerTest.assertEquals(this.password, new LoginQuery().getPassword(this.userName));

    }

    @Test
    @After
    public void cleanUpTestData() {
        this.handler.deleteAccount(this.userName);
    }
}