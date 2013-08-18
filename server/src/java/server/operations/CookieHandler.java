package server.operations;

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

        final String cookieID = new PasswordEncryptor().generateEncryptedPassword();
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

    public final boolean deleteCookie(final Cookie cookie) {
        return new CookieQuery().removeCookie(cookie.getValue());
    }
}
