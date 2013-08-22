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
	private final EntityManager em;

	public CookieQuery() {
		this.em = HibernateUtil.getEntityManager();
	}

	/**
	 * Creates a new Cookie
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
	 * Removes existing Cookie
	 * 
	 * @param cookieString
	 * @return
	 */
	public boolean removeCookie(final String cookieString) {
		final Cookie cookie = this.getCookie(cookieString);

		if (cookie == null) {
			return false;
		} else {
			this.em.getTransaction().begin();
			this.em.remove(cookie);
			this.em.getTransaction().commit();
			return true;
		}
	}

	public void removeInvalidCookies(final Date date) {
		@SuppressWarnings("unchecked")
		final List<Cookie> cookies = this.em.createNativeQuery(
				"select * from Cookie WHERE invalidForm <='" + date + "'",
				Cookie.class).getResultList();

		this.em.getTransaction().begin();
		for (final Cookie cookie : cookies) {
			this.em.remove(cookie);
		}
		this.em.getTransaction().commit();
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
