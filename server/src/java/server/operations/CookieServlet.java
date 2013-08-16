package server.operations;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieServlet {

	public void doGet(final String userName, final HttpServletResponse response) {

		final String cookieID = new PasswordEncryptor()
				.generateEncryptedPassword();
		final Cookie cookie = new Cookie("NSA_Cookie", cookieID);
		cookie.setValue(cookieID);
		cookie.setMaxAge(600);
		// TODO: save password in the DB / temp storage
		response.addCookie(cookie);
	}

	public boolean validateKey(final HttpServletRequest request) {

		final Cookie[] cookies = request.getCookies();
		String clientKey = "";
		boolean valid = false;
		if (cookies != null) {
			for (final Cookie cookie : cookies) {
				if ("NSA_Cookie".equals(cookie.getName())) {
					clientKey = cookie.getValue();
				}
			}
			// TODO: Check the DB for the key
			final String dbKey = null;

			if (clientKey.equals(dbKey)) {
				valid = true;
			}
		}
		return valid;
	}

}
