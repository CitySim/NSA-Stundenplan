package server.operations;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * CookieManager is a simple utilty for handling cookies when working with
 * java.net.URL and java.net.URLConnection objects.
 * 
 * 
 * Cookiemanager cm = new CookieManager(); URL url = new
 * URL("http://www.hccp.org/test/cookieTest.jsp");
 * 
 * . . .
 * 
 * // getting cookies: URLConnection conn = url.openConnection();
 * conn.connect();
 * 
 * // setting cookies cm.storeCookies(conn);
 * cm.setCookies(url.openConnection());
 * 
 * @author Ian Brown
 * 
 **/

public class CookieManager {

	private final Map store;

	private static final String SET_COOKIE = "Set-Cookie";
	private static final String COOKIE_VALUE_DELIMITER = ";";
	private static final String PATH = "path";
	private static final String EXPIRES = "expires";
	private static final String DATE_FORMAT = "EEE, dd-MMM-yyyy hh:mm:ss z";
	private static final String SET_COOKIE_SEPARATOR = "; ";
	private static final String COOKIE = "Cookie";

	private static final char NAME_VALUE_SEPARATOR = '=';
	private static final char DOT = '.';

	private final DateFormat dateFormat;

	public CookieManager() {

		this.store = new HashMap();
		this.dateFormat = new SimpleDateFormat(DATE_FORMAT);
	}

	/**
	 * Retrieves and stores cookies returned by the host on the other side of
	 * the the open java.net.URLConnection.
	 * 
	 * The connection MUST have been opened using the connect() method or a
	 * IOException will be thrown.
	 * 
	 * @param conn
	 *            a java.net.URLConnection - must be open, or IOException will
	 *            be thrown
	 * @throws java.io.IOException
	 *             Thrown if conn is not open.
	 */
	public void storeCookies(final URLConnection conn) throws IOException {

		// let's determine the domain from where these cookies are being sent
		final String domain = this.getDomainFromHost(conn.getURL().getHost());

		Map domainStore; // this is where we will store cookies for this domain

		// now let's check the store to see if we have an entry for this domain
		if (this.store.containsKey(domain)) {
			// we do, so lets retrieve it from the store
			domainStore = (Map) this.store.get(domain);
		} else {
			// we don't, so let's create it and put it in the store
			domainStore = new HashMap();
			this.store.put(domain, domainStore);
		}

		// OK, now we are ready to get the cookies out of the URLConnection

		String headerName = null;
		for (int i = 1; (headerName = conn.getHeaderFieldKey(i)) != null; i++) {
			if (headerName.equalsIgnoreCase(SET_COOKIE)) {
				final Map cookie = new HashMap();
				final StringTokenizer st = new StringTokenizer(
						conn.getHeaderField(i), COOKIE_VALUE_DELIMITER);

				// the specification dictates that the first name/value pair
				// in the string is the cookie name and value, so let's handle
				// them as a special case:

				if (st.hasMoreTokens()) {
					final String token = st.nextToken();
					final String name = token.substring(0,
							token.indexOf(NAME_VALUE_SEPARATOR));
					final String value = token.substring(
							token.indexOf(NAME_VALUE_SEPARATOR) + 1,
							token.length());
					domainStore.put(name, cookie);
					cookie.put(name, value);
				}

				while (st.hasMoreTokens()) {
					final String token = st.nextToken();
					cookie.put(
							token.substring(0,
									token.indexOf(NAME_VALUE_SEPARATOR))
									.toLowerCase(), token.substring(
									token.indexOf(NAME_VALUE_SEPARATOR) + 1,
									token.length()));
				}
			}
		}
	}

	/**
	 * Prior to opening a URLConnection, calling this method will set all
	 * unexpired cookies that match the path or subpaths for thi underlying URL
	 * 
	 * The connection MUST NOT have been opened method or an IOException will be
	 * thrown.
	 * 
	 * @param conn
	 *            a java.net.URLConnection - must NOT be open, or IOException
	 *            will be thrown
	 * @throws java.io.IOException
	 *             Thrown if conn has already been opened.
	 */
	public void setCookies(final URLConnection conn) throws IOException {

		// let's determine the domain and path to retrieve the appropriate
		// cookies
		final URL url = conn.getURL();
		final String domain = this.getDomainFromHost(url.getHost());
		final String path = url.getPath();

		final Map domainStore = (Map) this.store.get(domain);
		if (domainStore == null) {
			return;
		}
		final StringBuffer cookieStringBuffer = new StringBuffer();

		final Iterator cookieNames = domainStore.keySet().iterator();
		while (cookieNames.hasNext()) {
			final String cookieName = (String) cookieNames.next();
			final Map cookie = (Map) domainStore.get(cookieName);
			// check cookie to ensure path matches and cookie is not expired
			// if all is cool, add cookie to header string
			if (this.comparePaths((String) cookie.get(PATH), path)
					&& this.isNotExpired((String) cookie.get(EXPIRES))) {
				cookieStringBuffer.append(cookieName);
				cookieStringBuffer.append("=");
				cookieStringBuffer.append((String) cookie.get(cookieName));
				if (cookieNames.hasNext()) {
					cookieStringBuffer.append(SET_COOKIE_SEPARATOR);
				}
			}
		}
		try {
			conn.setRequestProperty(COOKIE, cookieStringBuffer.toString());
		} catch (final java.lang.IllegalStateException ise) {
			final IOException ioe = new IOException(
					"Illegal State! Cookies cannot be set on a URLConnection that is already connected. "
							+ "Only call setCookies(java.net.URLConnection) AFTER calling java.net.URLConnection.connect().");
			throw ioe;
		}
	}

	private String getDomainFromHost(final String host) {
		if (host.indexOf(DOT) != host.lastIndexOf(DOT)) {
			return host.substring(host.indexOf(DOT) + 1);
		} else {
			return host;
		}
	}

	private boolean isNotExpired(final String cookieExpires) {
		if (cookieExpires == null) {
			return true;
		}
		final Date now = new Date();
		try {
			return (now.compareTo(this.dateFormat.parse(cookieExpires))) <= 0;
		} catch (final java.text.ParseException pe) {
			pe.printStackTrace();
			return false;
		}
	}

	private boolean comparePaths(final String cookiePath,
			final String targetPath) {
		if (cookiePath == null) {
			return true;
		} else if (cookiePath.equals("/")) {
			return true;
		} else if (targetPath.regionMatches(0, cookiePath, 0,
				cookiePath.length())) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Returns a string representation of stored cookies organized by domain.
	 */

	@Override
	public String toString() {
		return this.store.toString();
	}

	public static void main(final String[] args) {
		final CookieManager cm = new CookieManager();
		try {
			final URL url = new URL("http://www.hccp.org/test/cookieTest.jsp");
			final URLConnection conn = url.openConnection();
			conn.connect();
			cm.storeCookies(conn);
			System.out.println(cm);
			cm.setCookies(url.openConnection());
		} catch (final IOException ioe) {
			ioe.printStackTrace();
		}
	}

}
