package server.operations.email;

import java.util.ArrayList;
import java.util.List;

import server.entities.Form;
import server.entities.Newsletter;
import server.entities.Replacement;
import server.exceptions.ScheduleCreationException;
import server.queries.NewsletterQuery;

/**
 * Used to create the eMails to send.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

class EmailCreator {

	final ArrayList<EmailObject> createConfirmationMail(final Form form, final String email) {

		final ArrayList<EmailObject> emailList = new ArrayList<EmailObject>();

		final EmailObject emailObject = new EmailObject();
		emailList.add(emailObject);

		final ArrayList<String> emailAddresList = emailObject.getEmailAddressList();

		final String emailText = new EmailTextCreator().generateConformationText(form, email);

		new EmailContentCreator().createMailContent(emailText, null, emailObject);

		emailAddresList.add(email);

		return emailList;

	}

	final ArrayList<EmailObject> createNewsLetterMails(final Replacement replacement) throws ScheduleCreationException {

		final ArrayList<EmailObject> emailList = new ArrayList<EmailObject>();

		final EmailObject emailObject = new EmailObject();
		emailList.add(emailObject);

		final ArrayList<String> emailAddresList = emailObject.getEmailAddressList();

		final String emailText = new EmailTextCreator().generateScheduleChangeText(replacement);

		new EmailContentCreator().createMailContent(emailText, null, emailObject);

		final List<Newsletter> newsLetterList = new NewsletterQuery().getAllNewsletters(replacement.getForm());
		for (final Newsletter newsLetter : newsLetterList) {
			emailAddresList.add(newsLetter.getEmail().getEMailAddress());
		}

		return emailList;
	}

	final ArrayList<EmailObject> createCreationMail(final String userName, final String password, final String eMailAddress) {

		final ArrayList<EmailObject> emailList = new ArrayList<EmailObject>();

		final EmailObject emailObject = new EmailObject();
		emailList.add(emailObject);

		final ArrayList<String> emailAddresList = emailObject.getEmailAddressList();

		final String emailText = new EmailTextCreator().generateUserCreationText(userName, password);

		new EmailContentCreator().createMailContent(emailText, null, emailObject);

		emailAddresList.add(eMailAddress);

		return emailList;
	}

	final ArrayList<EmailObject> createPasswordChangeMail(final String eMailAddress, final String userName, final String password) {

		final ArrayList<EmailObject> emailList = new ArrayList<EmailObject>();

		final EmailObject emailObject = new EmailObject();
		emailList.add(emailObject);

		final ArrayList<String> emailAddresList = emailObject.getEmailAddressList();

		final String emailText = new EmailTextCreator().generatePasswordChangeText(userName, password);

		new EmailContentCreator().createMailContent(emailText, null, emailObject);

		emailAddresList.add(eMailAddress);

		return emailList;
	}

	// TODO testen, überprüfen, ggf. refactorn
	public ArrayList<EmailObject> createRemoveRegistrationMail(final Newsletter newsletter) {

		final ArrayList<EmailObject> emailList = new ArrayList<EmailObject>();

		final EmailObject emailObject = new EmailObject();
		emailList.add(emailObject);

		final ArrayList<String> emailAddresList = emailObject.getEmailAddressList();

		final String emailText = new EmailTextCreator().generateRemoveRegistrationText(newsletter);

		new EmailContentCreator().createMailContent(emailText, null, emailObject);

		emailAddresList.add(newsletter.getEmail().getEMailAddress());

		return emailList;
	}
}
