package server.queries;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.itextpdf.text.log.SysoCounter;

import server.entities.Cookie;
import server.entities.EmailAddress;
import server.entities.Login;
import server.persistence.HibernateUtil;

public class CookieQuery {
	private final EntityManager em;

	public CookieQuery() {
		this.em = HibernateUtil.getEntityManager();
	}


	public void createCookie(String cookieString) {	
		this.em.getTransaction().begin();
		Cookie cookie = new Cookie();
		cookie.setCookie(cookieString);
		this.em.persist(cookie);
		this.em.getTransaction().commit();
	}

	public boolean existsCookie(String cookie){
		return getCookie(cookie) == null ? false : true;
	}
	
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
	
	private Cookie getCookie(String cookie){
		@SuppressWarnings("unchecked")
		final List<Cookie> cookieList = this.em.createNativeQuery(
				"select * from Cookie WHERE cookie ='"+cookie+"'", Cookie.class).getResultList();
		return cookieList.size() == 1 ? cookieList.get(0) : null;
	}
}
