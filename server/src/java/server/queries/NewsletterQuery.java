package server.queries;

import java.util.List;

import javax.persistence.EntityManager;

import server.entities.EmailAddress;
import server.entities.Form;
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
	 * @return
	 */
	public void addEmail(final String mail, final String schoolClass) {
		this.em.getTransaction().begin();

		EmailAddress email = this.getEmail(mail);
		if (email == null) {
			email = new EmailAddress();
			email.setEMailAddress(mail);
			this.em.persist(email);
		}
		final Form form = this.getForm(schoolClass);

		final Newsletter newsletter = new Newsletter();
		newsletter.setEmail(email);
		newsletter.setForm(form);
		this.em.persist(newsletter);
		this.em.getTransaction().commit();
	}

	/**
	 * Removes existing Email
	 * 
	 * @param cookieString
	 * @return
	 */
	public boolean removeEmail(final String mail, final String schoolClass) {
		final List<Newsletter> newsletters = this.getAllNewsletters();
		Newsletter singleNewsletter = null;
		for (final Newsletter newsletter : newsletters) {
			if (newsletter.getEmail().getEMailAddress() == mail
					&& newsletter.getForm().getDescription() == schoolClass) {
				singleNewsletter = newsletter;
			}
		}
		if (singleNewsletter == null) {
			return false;
		} else {
			this.em.getTransaction().begin();
			this.em.remove(singleNewsletter);
			this.em.getTransaction().commit();
			return true;
		}
	}

	/**
	 * Gets existing Email adress
	 * 
	 * @param formString
	 * @return
	 */
	private EmailAddress getEmail(final String mail) {
		final EmailAddress email = (EmailAddress) this.em.createNativeQuery(
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
	private Form getForm(final String formString) {
		final Form form = (Form) this.em.createNativeQuery(
				"select * from Klasse WHERE bezeichnung ='" + formString + "'",
				Form.class).getSingleResult();
		return form;
	}

	/**
	 * Gets the full Newsletterlist
	 * 
	 * @return
	 */
	public List<Newsletter> getAllNewsletters() {
		@SuppressWarnings("unchecked")
		final List<Newsletter> newsletter = this.em.createNativeQuery(
				"select * from Newsletter", Newsletter.class).getResultList();
		return newsletter;
	}

}
