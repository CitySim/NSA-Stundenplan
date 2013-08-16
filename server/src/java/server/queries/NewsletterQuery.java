package server.queries;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.itextpdf.text.log.SysoCounter;

import server.entities.Cookie;
import server.entities.EmailAddress;
import server.entities.Form;
import server.entities.Login;
import server.entities.Newsletter;
import server.persistence.HibernateUtil;

/**
 * Creates/Deletes/Checks the Cookies and inputs them into the Database
 * @author oleg.scheltow
 *
 */
public class NewsletterQuery {
	private final EntityManager em;

	public NewsletterQuery() {
		this.em = HibernateUtil.getEntityManager();
	}

	/**
	 * Creates a new Cookie
	 * @param cookieString
	 */
	public void addEmail(String mail,String schoolClass) {	
		em.getTransaction().begin();
		
		EmailAddress email = new EmailAddress();
		email.setEMailAddress(mail);
		em.persist(email);	
		
		Form form = getForm(schoolClass);
		
		Newsletter newsletter = new Newsletter();
		newsletter.setEmail(email);
		newsletter.setForm(form);
		em.persist(newsletter);
		em.getTransaction().commit();
	}

	/**
	 * Removes existing Cookie
	 * @param cookieString
	 * @return
	 */
	public boolean removeEmail(String mail){
		Newsletter newsletter = getNewsletter(mail);
		
		if(newsletter == null){
			return false;
		}else{
			em.getTransaction().begin();
			em.remove(newsletter);
			em.getTransaction().commit();
			return true;
		}
	}
	
	/**
	 * Get the specified Cookie
	 * @param cookie
	 * @return
	 */
	private Form getForm(String formString){
		@SuppressWarnings("unchecked")
		Form form =  (Form) this.em.createNativeQuery(
				"select * from Klasse WHERE bezeichnung ='"+formString+"'", Form.class).getSingleResult();
		return form;
	}
	
	// TODO --> Is wrong! First get Email and then get the Newsletter that has the email
	private Newsletter getNewsletter (String email){
		Newsletter newsletter =  (Newsletter) this.em.createNativeQuery(
				"select * from Newsletter WHERE bezeichnung ='"+email+"'", Newsletter.class).getSingleResult();

		return newsletter;
	}
	
	
}
