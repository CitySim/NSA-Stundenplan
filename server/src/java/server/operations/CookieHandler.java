package server.operations;

import java.util.Date;

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
		final DateHelper dateHelper = new DateHelper();
		dateHelper.addTime(0, 1, 0, 0, 0, 0);
		new CookieQuery().createCookie(cookieID,
				dateHelper.parseStringToDate(dateHelper.getFullDate()));

		return cookie;
	}

	final boolean validateCookie(final Cookie cookie) {

		String cookieValue = "";

		if ("NSA-Cookie".equals(cookie.getName())) {
			cookieValue = cookie.getValue();
		}

		if (new CookieQuery().getCookie(cookieValue) != null) {
			return true;
		} else {
			return false;
		}
	}

	public final void deleteInvalidCookies() {
		new CookieQuery().removeInvalidCookies(new Date());
	}

	final boolean deleteCookie(final Cookie cookie) {
		return new CookieQuery().removeCookie(cookie.getValue());
	}
}
