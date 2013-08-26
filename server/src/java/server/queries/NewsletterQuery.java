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

	public boolean confirmEmail(final Form form, final String mail) {
		this.em.getTransaction().begin();

		EmailAddress email = this.getEmail(mail);
		if (email == null) {
			email = new EmailAddress();
			email.setEMailAddress(mail);
			this.em.persist(email);
		}

		final Newsletter newsletter = new Newsletter();
		newsletter.setEmail(email);
		newsletter.setForm(form);
		this.em.persist(newsletter);
		this.em.getTransaction().commit();
		return true;
	}

	/**
	 * Removes existing Newsletter registration
	 * 
	 */
	public boolean removeNewsletter(final Newsletter newsletter) {
			return removeFromDB(newsletter);
	}

	/**
	 * Gets existing Email address
	 * 
	 * @param formString
	 * @return EmailAddress
	 */
	private EmailAddress getEmail(final String mail) {
		return (EmailAddress) getSingleResult(em
				.createNativeQuery(
						"select * from emailaddress WHERE eMailAddress ='"
								+ mail + "'", EmailAddress.class));
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

	public Newsletter getNewsletter(int mailID, int formID) {
		return (Newsletter) getSingleResult(em.createNativeQuery(
				"select * from Newsletter where email_id=" + mailID
						+ " AND form_idKlasse =" + formID + "",
				Newsletter.class));
	}
}