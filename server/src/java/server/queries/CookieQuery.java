package server.queries;

import javax.persistence.EntityManager;
import server.entities.Cookie;
import server.persistence.HibernateUtil;

/**
 * Creates/Deletes/Checks the Cookies and inputs them into the Database
 * @author oleg.scheltow
 *
 */
public class CookieQuery {
	private final EntityManager em;

	public CookieQuery() {
		this.em = HibernateUtil.getEntityManager();
	}

	/**
	 * Creates a new Cookie
	 * @param cookieString
	 */
	public void createCookie(String cookieString) {	
		this.em.getTransaction().begin();
		Cookie cookie = new Cookie();
		cookie.setCookie(cookieString);
		this.em.persist(cookie);
		this.em.getTransaction().commit();
	}

	/**
	 * Checks for existing Cookie
	 * @param cookie
	 * @return
	 */
	public boolean existsCookie(String cookie){
		return getCookie(cookie) == null ? false : true;
	}
	
	/**
	 * Removes existing Cookie
	 * @param cookieString
	 * @return
	 */
	public boolean removeCookie(String cookieString){
		Cookie cookie = getCookie(cookieString);
		
		if(cookie == null){
			return false;
		}else{
			this.em.getTransaction().begin();
			this.em.remove(cookie);
			this.em.getTransaction().commit();
			return true;
		}
	}
	
	/**
	 * Get the specified Cookie
	 * @param cookie
	 * @return
	 */
	private Cookie getCookie(String cookieString){
		Cookie cookie = (Cookie) this.em.createNativeQuery(
				"select * from Cookie WHERE cookie ='"+cookieString+"'", Cookie.class).getSingleResult();
		return cookie;
	}
}
