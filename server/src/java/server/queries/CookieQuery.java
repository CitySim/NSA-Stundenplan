package server.queries;

import java.util.Date;
import java.util.List;

import server.entities.Cookie;

/**
 * Creates/Deletes/Checks the Cookies and inputs them into the Database.
 * 
 * @author oleg.scheltow
 * 
 */
public class CookieQuery extends QueryResult {

	public CookieQuery() {
		super();
	}

	/**
	 * Creates a new Cookie.
	 * 
	 * @param cookieString
	 */
	public void createCookie(final String cookieString, final Date date) {
		this.em.getTransaction().begin();
		final Cookie cookie = new Cookie();
		cookie.setCookie(cookieString);
		cookie.setInvalidForm(date);
		this.em.persist(cookie);
		this.em.getTransaction().commit();
	}

	/**
	 * Removes existing Cookie.
	 * 
	 * @param cookieString
	 * @return boolean successful
	 */
	public boolean removeCookie(final String cookieString) {
		final Cookie cookie = this.getCookie(cookieString);
		return this.removeFromDB(cookie);
	}

	public void removeInvalidCookies(final Date date) {
		@SuppressWarnings("unchecked")
		final List<Cookie> cookies = this.em.createNativeQuery("select * from Cookie WHERE invalidForm <='" + date + "'", Cookie.class)
				.getResultList();
		if (cookies.size() != 0) {
			this.em.getTransaction().begin();
			for (final Cookie cookie : cookies) {
				this.em.remove(cookie);
			}
			this.em.getTransaction().commit();
		}
	}

	/**
	 * Get the specified Cookie.
	 * 
	 * @param cookie
	 * @return boolean successful
	 */
	public Cookie getCookie(final String cookieString) {
		return (Cookie) this.getSingleResult(this.em.createNativeQuery("select * from Cookie WHERE cookie ='" + cookieString + "'", Cookie.class));
	}

}
