package server.operations;

import javax.servlet.http.Cookie;

public class CookieValidator {

	public Cookie createCookie() {

		final String cookieID = new PasswordEncryptor()
				.generateEncryptedPassword();
		final Cookie cookie = new Cookie("NSA_Cookie", cookieID);
		cookie.setValue(cookieID);
		cookie.setMaxAge(600);
		// TODO: save password in the DB / temp storage
		return cookie;
	}

	public boolean validateCookie(final Cookie cookie) {

		String clientKey = "";
		boolean valid = false;

		if ("NSA_Cookie".equals(cookie.getName())) {
			clientKey = cookie.getValue();
		}

		// TODO: Check the DB for the key
		final String dbKey = null;

		if (clientKey.equals(dbKey)) {
			valid = true;
		}

		return valid;
	}

}
