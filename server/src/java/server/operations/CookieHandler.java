package server.operations;

import javax.servlet.http.Cookie;

import server.queries.CookieQuery;

public class CookieHandler {

	public Cookie createCookie() {

		final String cookieID = new PasswordEncryptor()
				.generateEncryptedPassword();
		final Cookie cookie = new Cookie("NSA-Cookie", cookieID);
		cookie.setValue(cookieID);
		cookie.setMaxAge(2592000);
		new CookieQuery().createCookie(cookieID);

		return cookie;
	}

	public boolean validateCookie(final Cookie cookie) {

		String cookieValue = "";

		if ("NSA-Cookie".equals(cookie.getName())) {
			cookieValue = cookie.getValue();
		}

		return new CookieQuery().existsCookie(cookieValue);
	}

	public boolean deleteCookie(final Cookie cookie) {
		return new CookieQuery().removeCookie(cookie.getValue());
	}
}
