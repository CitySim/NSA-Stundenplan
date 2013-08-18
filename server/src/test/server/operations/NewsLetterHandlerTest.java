package server.operations;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.exceptions.EmailSendingException;

/**
 * Test for the generation of the newsLetterUrls.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class NewsLetterHandlerTest extends TestCase {

    private NewsLetterHandler handler;

    private String schoolClass;
    private String eMailAddress;

    @Override
    @Before
    public void setUp() throws Exception {
        this.handler = new NewsLetterHandler();
        this.schoolClass = "it1a";
        this.eMailAddress = "test@test.de";
    }

    @Test
    public void testUrlCreation() {

        // TODO Edit url
        final String expectedUrl = "nsa blabla/add_" + this.eMailAddress + "_to:" + this.schoolClass;

        NewsLetterHandlerTest.assertEquals(
                expectedUrl,
                this.handler.generateRegistrationLink(this.schoolClass, this.eMailAddress));
    }

    @Test
    public void testAddressCreation() throws EmailSendingException {

        boolean success = false;

        try {
            NewsLetterHandlerTest.assertTrue(this.handler.addAddress(this.eMailAddress, this.schoolClass));
        } catch (final EmailSendingException e) {
        }

        // TODO get List from DB
        final ArrayList<String> newsLetterList = null;

        for (final String emailAddress : newsLetterList) {
            if (emailAddress.equals(this.eMailAddress)) {
                success = true;
            }
        }
        NewsLetterHandlerTest.assertEquals(true, success);

    }

    @After
    @Test
    public void testAddressRemoving() {

        NewsLetterHandlerTest.assertTrue(this.handler.removeAddress(this.eMailAddress, this.schoolClass));
    }
}
