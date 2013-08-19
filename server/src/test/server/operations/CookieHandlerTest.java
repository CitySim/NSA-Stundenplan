package server.operations;

import javax.servlet.http.Cookie;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for cookie creation and validation.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class CookieHandlerTest extends TestCase {

	private CookieHandler handler;
	private Cookie cookie;

	@Override
	@Before
	public void setUp() throws Exception {
		this.handler = new CookieHandler();
	}

	@Test
	public void testCookieCreation() {
		this.cookie = this.handler.createCookie();
		CookieHandlerTest.assertTrue(this.handler.validateCookie(this.cookie));
		CookieHandlerTest.assertTrue(this.handler.deleteCookie(this.cookie));

	}
}
