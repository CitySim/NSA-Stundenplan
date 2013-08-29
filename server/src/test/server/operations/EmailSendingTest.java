package server.operations;

import java.util.Date;

import javax.persistence.EntityManager;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import server.entities.EmailAddress;
import server.entities.Form;
import server.entities.Newsletter;
import server.entities.Replacement;
import server.entities.Room;
import server.entities.Subject;
import server.entities.Teacher;
import server.exceptions.EmailSendingException;
import server.exceptions.ScheduleCreationException;
import server.operations.email.EmailJobHelper;
import server.persistence.DataCreationHelper;
import server.persistence.HibernateUtil;

/**
 * Test for exception email sending.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class EmailSendingTest extends TestCase {

	private EmailJobHelper helper;
	private Form form;
	private EntityManager em;
	private DataCreationHelper dataHelper;
	@Override
	@Before
	public void setUp() {
		this.helper = new EmailJobHelper();
		this.form = new Form();
		this.form.setDescription("it1a");

		this.em = HibernateUtil.getEntityManager();
		this.dataHelper = new DataCreationHelper(this.em);
	}

	@Test
	public void testEmailSending() {
		try {
			try {
				this.helper.sendNewsLetterMail(this.getExistingReplacement());
			} catch (final ScheduleCreationException e) {
				fail();
			}
			this.helper.sendConfirmationMail(this.form, "test@localhost");
			this.helper.sendCreationMail("test@localhost", "test", "test");
			this.helper.sendPasswordChangeMail("test@localhost", "test", "test");
			this.helper.sendRemoveRegistrationMail(this.getExistingNewsletter());
		} catch (final EmailSendingException e) {
			EmailSendingTest.fail();
		}
	}

	
	private Replacement getExistingReplacement() {
		return em.find(Replacement.class, 1);
	}

	private Newsletter getExistingNewsletter(){
		
		return em.find(Newsletter.class,1);
	}
}