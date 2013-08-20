package server.queries;

import java.util.Date;
import java.util.List;

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
	public void createCookie(String cookieString, Date date) {	
		em.getTransaction().begin();
		Cookie cookie = new Cookie();
		cookie.setCookie(cookieString);
		cookie.setInvalidForm(date);
		em.persist(cookie);
		em.getTransaction().commit();
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
			em.getTransaction().begin();
			em.remove(cookie);
			em.getTransaction().commit();
			return true;
		}
	}
	
	public List<Cookie> getInvalidCookies(Date date){
		@SuppressWarnings("unchecked")
		List<Cookie> cookies = this.em.createNativeQuery(
				"select * from Cookie WHERE invalidForm <='"+date+"'", Cookie.class).getResultList();
		em.getTransaction().begin();
		for (Cookie cookie : cookies) {
			em.remove(cookie);
		}
		em.getTransaction().commit();
		return cookies;
	}
	
	/**
	 * Get the specified Cookie
	 * @param cookie
	 * @return
	 */
	public Cookie getCookie(String cookieString){
		Cookie cookie = (Cookie) this.em.createNativeQuery(
				"select * from Cookie WHERE cookie ='"+cookieString+"'", Cookie.class).getSingleResult();
		return cookie;
	}
}
