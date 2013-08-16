package server.operations.test;

import javax.servlet.http.Cookie;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.operations.CookieValidator;

/**
 * Test for cookie creation and validation.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class CookieServletTest extends TestCase {

	private CookieValidator validator;

	@Override
	@Before
	public void setUp() throws Exception {
		this.validator = new CookieValidator();
	}

	@Test
	public void testCookieCreation() {

		final Cookie cookie = this.validator.createCookie();
		CookieServletTest.assertTrue(this.validator.validateCookie(cookie));
	}

	@After
	@Test
	public void cleanUpTestData() {
		// TODO delete cookie
	}
}
