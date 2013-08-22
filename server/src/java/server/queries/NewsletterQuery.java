package server.queries;

import java.util.List;

import server.entities.EmailAddress;
import server.entities.Form;
import server.entities.Newsletter;
import server.entities.Teacher;

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
		Form form = this.getForm(schoolClass);
		// FIXME
		if (form == null) {
			Teacher teacher = new Teacher();
			teacher.setFirstname("Werner");
			teacher.setName("Hermann");
			teacher.setShortName("HR");
			this.em.persist(teacher);
			form = new Form();
			form.setDescription("it1a");
			form.setTeacher(teacher);
			this.em.persist(form);
		}
		// FIXME FORM SHOULD NOOOOOOOOOOT BE HERE
		
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
	public boolean removeEmail(final String mail, final String formString) {
		EmailAddress email= getEmail(mail);
		Form form = getForm(formString);
		
		if(email != null && form != null){
			Newsletter newsletter = getNewsletter(email.getId(),form.getId());
			return removeFromDB(newsletter);
		}else{
			return false;
		}
	}


	/**
	 * Gets existing Email address
	 * 
	 * @param formString
	 * @return EmailAddress
	 */
	private EmailAddress getEmail(final String mail) {
		return (EmailAddress) getSingleResult(em.createNativeQuery(
				"select * from emailaddress WHERE eMailAddress ='" + mail + "'",
				EmailAddress.class));
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
	
	public Newsletter getNewsletter(int mailID, int formID){
		return (Newsletter) getSingleResult(em.createNativeQuery(
				"select * from Newsletter where email_id=" + mailID + " AND form_idKlasse =" + formID +"",
				Newsletter.class));		
	}

}
