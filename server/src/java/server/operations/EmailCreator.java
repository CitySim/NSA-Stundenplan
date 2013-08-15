package server.operations;

import java.io.File;
import java.util.ArrayList;

import server.entities.Timetable;
import dennis.markmann.MyLibraries.DefaultJobs.Email.EmailContentCreator;
import dennis.markmann.MyLibraries.DefaultJobs.Email.EmailObject;

public class EmailCreator {

	final ArrayList<EmailObject> createConfirmationMail(
			final String eMailAddress, final String schoolClass) {

		final ArrayList<EmailObject> emailList = new ArrayList<EmailObject>();

		final EmailObject emailObject = new EmailObject();
		emailList.add(emailObject);

		final ArrayList<String> emailAddresList = emailObject
				.getEmailAddressList();

		final String emailText = new EmailTextCreator()
				.generateConformationText(schoolClass, eMailAddress);

		new EmailContentCreator().createMailContent(emailText, null,
				emailObject);

		emailAddresList.add(eMailAddress);

		return emailList;

	}

	ArrayList<EmailObject> createEmailObjects(final Timetable entityList) {

		final ArrayList<EmailObject> emailList = new ArrayList<EmailObject>();

		final EmailObject emailObject = new EmailObject();
		emailList.add(emailObject);

		final ArrayList<String> emailAddresList = emailObject
				.getEmailAddressList();

		final String emailText = new EmailTextCreator()
				.generateScheduleChangeText();
		final File file = new File(new FilePrinter().printAsPDF());

		new EmailContentCreator().createMailContent(emailText, file,
				emailObject);

		// for (final EmailAddresse eMailAddresse : newsLetter
		// .geteMailAddressList()) {
		// emailAddresList.add(eMailAddresse.geteMailAddress());
		// }
		// }

		return emailList;
	}

	ArrayList<EmailObject> createCreationMail(final String userName,
			final String password, final String eMailAddress) {

		final ArrayList<EmailObject> emailList = new ArrayList<EmailObject>();

		final EmailObject emailObject = new EmailObject();
		emailList.add(emailObject);

		final ArrayList<String> emailAddresList = emailObject
				.getEmailAddressList();

		final String emailText = new EmailTextCreator()
				.generateUserCreationText(userName, password);

		new EmailContentCreator().createMailContent(emailText, null,
				emailObject);

		emailAddresList.add(eMailAddress);

		return emailList;
	}

	public ArrayList<EmailObject> createPasswordChangeMail(
			final String eMailAddress, final String userName,
			final String password) {

		final ArrayList<EmailObject> emailList = new ArrayList<EmailObject>();

		final EmailObject emailObject = new EmailObject();
		emailList.add(emailObject);

		final ArrayList<String> emailAddresList = emailObject
				.getEmailAddressList();

		final String emailText = new EmailTextCreator()
				.generatePasswordChangeText(userName, password);

		new EmailContentCreator().createMailContent(emailText, null,
				emailObject);

		emailAddresList.add(eMailAddress);

		return emailList;
	}
}
