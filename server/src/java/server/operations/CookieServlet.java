package server.operations;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieServlet {

	public void doGet(final String userName, final HttpServletResponse response)
			throws ServletException, IOException {

		final Cookie cookie = new Cookie("NSA_Cookie", "IDTest");
		cookie.setValue(new PasswordEncryptor().generateEncryptedPassword());
		cookie.setMaxAge(600);
		// TODO: save password in the DB / temp storage
		response.addCookie(cookie);
	}

	public boolean validateKey(final HttpServletRequest req) {

		// get the attribute
		final String clientKey = (String) req.getAttribute("");
		// TODO: Check the DB for the key
		final String dbKey = null;

		if (clientKey.equals(dbKey)) {
			return true;
		}
		return false;

	}
}
