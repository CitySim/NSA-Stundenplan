package server.operations;

import java.util.ArrayList;

import javax.servlet.http.Cookie;

import server.queries.CookieQuery;

/**
 * Used to creates and validated session cookies for the client.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class CookieHandler {

	public final Cookie createCookie() {

		final String cookieID = new PasswordEncryptor()
				.generateEncryptedPassword();
		final Cookie cookie = new Cookie("NSA-Cookie", cookieID);
		cookie.setValue(cookieID);
		cookie.setMaxAge(2592000);
		new CookieQuery().createCookie(cookieID);

		return cookie;
	}

	public final boolean validateCookie(final Cookie cookie) {

		String cookieValue = "";

		if ("NSA-Cookie".equals(cookie.getName())) {
			cookieValue = cookie.getValue();
		}

		return new CookieQuery().existsCookie(cookieValue);
	}

	public final void deleteInvalidCookies() {

		final ArrayList<Cookie> cookieList = null;

		for (final Cookie cookie : cookieList) {
			if (!this.checkStillValid(cookie)) {
				this.deleteCookie(cookie);
			}
		}

	}

	public final boolean deleteCookie(final Cookie cookie) {
		return new CookieQuery().removeCookie(cookie.getValue());
	}

	private boolean checkStillValid(final Cookie cookie) {
		return true;

		// invalidDate für cookie aus der DB ziehen
		// wenn erreicht return false, sonst true
	}
}
