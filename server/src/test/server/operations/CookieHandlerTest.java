package server.operations;

import java.util.Date;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Variant.VariantListBuilder;
import javax.ws.rs.ext.RuntimeDelegate;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import server.exceptions.CookieInvalidException;
import server.queries.CookieQuery;

/**
 * Test for cookie creation and validation.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class CookieHandlerTest extends TestCase {

	private CookieHandler handler;

	@Override
	@Before
	public void setUp() {
		this.handler = new CookieHandler();
	}

	@Test
	public void testCookieCreation() {
		RuntimeDelegate.setInstance(new RuntimeDelegate() {

			@Override
			public VariantListBuilder createVariantListBuilder() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public UriBuilder createUriBuilder() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseBuilder createResponseBuilder() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <T> HeaderDelegate<T> createHeaderDelegate(final Class<T> arg0) {
				return null;
			}

			@Override
			public <T> T createEndpoint(final Application arg0, final Class<T> arg1) {
				// TODO Auto-generated method stub
				return null;
			}
		});

		final NewCookie cookie = this.handler.createCookie();

		try {
			CookieHandlerTest.assertTrue(this.handler.validateCookie(cookie.getValue()));
		} catch (final CookieInvalidException e) {
			fail();
		}
		CookieHandlerTest.assertTrue(this.handler.deleteCookie(cookie.getValue()));
	}

	@Test
	public void testInvalidCookie() {

		final CookieQuery query = new CookieQuery();

		final String cookieID = new PasswordEncryptor().generateEncryptedPassword();
		final DateHelper dateHelper = new DateHelper();
		dateHelper.addTime(0, 0, 0, 0, -10, 0);
		query.createCookie(cookieID, dateHelper.parseStringToDate(dateHelper.getFullDate()));
		CookieHandlerTest.assertNotNull(query.getCookie(cookieID));

		query.removeInvalidCookies(new Date());
		CookieHandlerTest.assertNull(query.getCookie(cookieID));

	}
}
