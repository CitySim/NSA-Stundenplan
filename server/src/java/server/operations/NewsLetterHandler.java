package server.operations;

import server.entities.Form;
import server.entities.Newsletter;
import server.entities.Replacement;
import server.exceptions.EmailAddressException;
import server.exceptions.EmailSendingException;
import server.operations.email.EmailJobHelper;
import server.queries.NewsletterQuery;

/**
 * Used to add newsletter entries on the database. Generates URLs for confirming
 * the registration / removing of eMailAddresses.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */
public class NewsLetterHandler {

	public final String generateRegistrationLink(final Form form, final String email) {
		return AccountHandler.URL_PREFIX + "confirm?id=" + form.getId() + "&email=" + email;
	}

	public final String generateRemoveLink(final Newsletter newsletter) {
		return AccountHandler.URL_PREFIX + "remove?id=" + newsletter.getId();
	}

	public final void addAddress(final Form form, final String email) throws EmailSendingException, EmailAddressException {
		new EmailJobHelper().sendConfirmationMail(form, email);
	}

	boolean removeAddress(final Newsletter newsletter) throws EmailSendingException, EmailAddressException {
		return new EmailJobHelper().sendRemoveRegistrationMail(newsletter);
	}

	public boolean confirmRegistration(final Form form, final String email) throws EmailSendingException, EmailAddressException {
		return new NewsletterQuery().confirmEmail(form, email);
	}

	public boolean removeRegistration(final Newsletter newsletter) {
		return new NewsletterQuery().removeNewsletter(newsletter);
	}

	public final void generateReplacementMail(Replacement replacement) throws EmailSendingException, EmailAddressException {
		new EmailJobHelper().sendNewsLetterMail(replacement);
	}
}