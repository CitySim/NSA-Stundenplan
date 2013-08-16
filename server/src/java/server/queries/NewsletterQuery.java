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
 * 
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
	 * 
	 * @param cookieString
	 */
	public void addEmail(String mail, String schoolClass) {
		em.getTransaction().begin();

		EmailAddress email = getEmail(mail);
		if(email == null){
			email = new EmailAddress();
			email.setEMailAddress(mail);
			em.persist(email);
		}
		Form form = getForm(schoolClass);

		Newsletter newsletter = new Newsletter();
		newsletter.setEmail(email);
		newsletter.setForm(form);
		em.persist(newsletter);
		em.getTransaction().commit();
	}

	/**
	 * Removes existing Email
	 * 
	 * @param cookieString
	 * @return
	 */
	public boolean removeEmail(String mail, String schoolClass) {
		List<Newsletter> newsletters = getAllNewsletters();
		Newsletter singleNewsletter = null;
		for (Newsletter newsletter : newsletters) {
			if (newsletter.getEmail().getEMailAddress() == mail
					&& newsletter.getForm().getDescription() == schoolClass) {
				singleNewsletter = newsletter;
			}
		}
		if (singleNewsletter == null) {
			return false;
		} else {
			em.getTransaction().begin();
			em.remove(singleNewsletter);
			em.getTransaction().commit();
			return true;
		}
	}

	
	/**
	 * Gets existing Email adress
	 * 
	 * @param formString
	 * @return
	 */
	private EmailAddress getEmail(String mail) {
		@SuppressWarnings("unchecked")
		EmailAddress email = (EmailAddress) this.em.createNativeQuery(
				"select * from Email WHERE eMailAddress ='" + mail + "'",
				Form.class).getSingleResult();
		return email;
	}
	/**
	 * Gets the existing schoolClass
	 * 
	 * @param formString
	 * @return
	 */
	private Form getForm(String formString) {
		@SuppressWarnings("unchecked")
		Form form = (Form) this.em.createNativeQuery(
				"select * from Klasse WHERE bezeichnung ='" + formString + "'",
				Form.class).getSingleResult();
		return form;
	}

	/**
	 * Gets the full Newsletterlist
	 * @return
	 */
	private List<Newsletter> getAllNewsletters() {
		@SuppressWarnings("unchecked")
		List<Newsletter> newsletter = em.createNativeQuery(
				"select * from Newsletter", Newsletter.class).getResultList();
		return newsletter;
	}

}
