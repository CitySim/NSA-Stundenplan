package server.queries;

import java.util.List;

import server.entities.EmailAddress;
import server.entities.Form;
import server.entities.Newsletter;

/**
 * Creates/Deletes/Checks the Cookies and inputs them into the Database
 * 
 * @author oleg.scheltow
 * 
 */
public class NewsletterQuery extends QueryResult {

	public NewsletterQuery() {
		super();
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
		// Get Newsletter where email.id = emailID und klasse.id=klasseID
		//
		final List<Newsletter> newsletters = this.getAllNewsletters();
		Newsletter singleNewsletter = null;
		for (final Newsletter newsletter : newsletters) {
			if (newsletter.getEmail().getEMailAddress() == mail
					&& newsletter.getForm().getDescription() == schoolClass) {
				singleNewsletter = newsletter;
			}
		}
		return this.removeFromDB(singleNewsletter);

	}

	/**
	 * Gets existing Email address
	 * 
	 * @param formString
	 * @return EmailAddress
	 */
	private EmailAddress getEmail(final String mail) {
		return (EmailAddress) this.getSingleResult(this.em
				.createNativeQuery(
						"select * from emailaddress WHERE eMailAddress ='"
								+ mail + "'", Form.class));
	}

	/**
	 * Gets the existing schoolClass
	 * 
	 * @param formString
	 * @return Form
	 */
	private Form getForm(final String formString) {
		return (Form) this.getSingleResult(this.em.createNativeQuery(
				"select * from Klasse WHERE bezeichnung ='" + formString + "'",
				Form.class));
	}

	/**
	 * Gets the full Newsletterlist
	 * 
	 * @return List<Newsletter>
	 */
	public List<Newsletter> getAllNewsletters() {
		@SuppressWarnings("unchecked")
		final List<Newsletter> newsletter = this.em.createNativeQuery(
				"select * from Newsletter", Newsletter.class).getResultList();
		return newsletter;
	}

}
