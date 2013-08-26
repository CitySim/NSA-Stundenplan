package server.operations;

import server.entities.Form;
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
// FIXME links erstellen
public class NewsLetterHandler {
	
	public final String generateRegistrationLink(final String schoolClass, final String eMailAddress) {

		final String url = "nsa blabla/" + "add_" + eMailAddress + "_to:" + schoolClass;

		// createLink containing our webSite URL + add + emailAddress + to
		// schoolClass

		return url;
	}

	public final String generateRemoveLink(final String schoolClass, final String eMailAddress) {

		final String url = "nsablabla/" + "remove_" + eMailAddress + "_from:" + schoolClass;

		// createLink containing our webSite URL + remove + emailAddress + to
		// schoolClass

		return url;
	}

	public final void addAddress(final String schoolClass, final String email
			) throws EmailSendingException {

		new EmailJobHelper().sendConfirmationMail(schoolClass, email);

	}

	public boolean removeAddress(final String email,
			final String schoolClass) throws EmailSendingException {

		return new EmailJobHelper().sendRemoveRegistrationMail(schoolClass, email);

	}

	public boolean confirmRegistration(Form schoolClass, String email) {

		return new NewsletterQuery().confirmEmail(schoolClass, email);

	}

	public boolean removeRegistration(Form schoolClass, String email) {
		return new NewsletterQuery().removeEmail(schoolClass, email);
	}

}