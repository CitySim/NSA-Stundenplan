package server.queries;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.AbstractQuery;

import server.entities.Cookie;
import server.persistence.HibernateUtil;

/**
 * Creates/Deletes/Checks the Cookies and inputs them into the Database
 * 
 * @author oleg.scheltow
 * 
 */
public class CookieQuery extends QueryResult {

	public CookieQuery() {
		super();
	}

	/**
	 * Creates a new Cookie
	 * 
	 * @param cookieString
	 */
	public void createCookie(final String cookieString, final Date date) {
		em.getTransaction().begin();
		final Cookie cookie = new Cookie();
		cookie.setCookie(cookieString);
		cookie.setInvalidForm(date);
		em.persist(cookie);
		em.getTransaction().commit();
	}

	/**
	 * Removes existing Cookie
	 * 
	 * @param cookieString
	 * @return
	 */
	public boolean removeCookie(final String cookieString) {
		final Cookie cookie = this.getCookie(cookieString);
		return removeFromDB(cookie);
	}

	public void removeInvalidCookies(final Date date) {
		@SuppressWarnings("unchecked")
		final List<Cookie> cookies = this.em.createNativeQuery(
				"select * from Cookie WHERE invalidForm <='" + date + "'",
				Cookie.class).getResultList();
		if(cookies.size() != 0){
			em.getTransaction().begin();
			for (final Cookie cookie : cookies) {
				em.remove(cookie);
			}
			em.getTransaction().commit();
		}
	}

	/**
	 * Get the specified Cookie
	 * 
	 * @param cookie
	 * @return
	 */
	public Cookie getCookie(final String cookieString) {
		return (Cookie) getSingleResult(em.createNativeQuery(
				"select * from Cookie WHERE cookie ='" + cookieString + "'",
				Cookie.class));
	}

}
