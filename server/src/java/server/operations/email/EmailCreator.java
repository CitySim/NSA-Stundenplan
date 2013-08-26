package server.operations.email;

import java.io.File;
import java.util.ArrayList;

import server.entities.Form;
import server.entities.Newsletter;
import server.entities.Timetable;
import server.exceptions.ScheduleCreationException;
import server.operations.FilePrinter;

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

	final ArrayList<EmailObject> createNewsLetterMails(final Timetable timeTable) throws ScheduleCreationException {

		final ArrayList<EmailObject> emailList = new ArrayList<EmailObject>();

		final EmailObject emailObject = new EmailObject();
		emailList.add(emailObject);

		// final ArrayList<String> emailAddresList =
		// emailObject.getEmailAddressList();

		final String emailText = new EmailTextCreator().generateScheduleChangeText();
		final File file = new FilePrinter().printAsPDF(timeTable);

		new EmailContentCreator().createMailContent(emailText, file, emailObject);

		// for (final EmailAddresse eMailAddresse : newsLetter
		// .geteMailAddressList()) {
		// emailAddresList.add(eMailAddresse.geteMailAddress());
		// }
		// }

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
	public ArrayList<EmailObject> createRemoveRegistrationMail(Newsletter newsletter) {
		
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
