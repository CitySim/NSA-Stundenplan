package server.operations;

import java.io.File;
import java.util.ArrayList;

import server.entities.Timetable;
import dennis.markmann.MyLibraries.DefaultJobs.Email.EmailContentCreator;
import dennis.markmann.MyLibraries.DefaultJobs.Email.EmailJob;
import dennis.markmann.MyLibraries.DefaultJobs.Email.EmailObject;
import dennis.markmann.MyLibraries.DefaultJobs.Email.EmailSettings;

/**
 * Helper class used to initialize the emailSending and to create the eMails to
 * send.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

class EmailJobHelper {

	private EmailSettings setEmailSettings(final String titel) {
		return new EmailSettings("nsa-stundenplan@gmx.de", "nsa-stundenplan",
				"nsa-stundenplan@gmx.de", titel, "smtp.gmx.net");
	}

	public final void sendMail(final Timetable entityList) {

		final ArrayList<EmailObject> emailList = this
				.createEmailObjects(entityList);
		new EmailJob().sendMail(
				this.setEmailSettings("NSA - Stundenplan Abweichung"),
				emailList);
	}

	final void sendConfirmationMail(final String eMailAddress,
			final String schoolClass) {

		final ArrayList<EmailObject> emailList = this.createConfirmationMail(
				eMailAddress, schoolClass);
		new EmailJob().sendMail(
				this.setEmailSettings("NSA - RegistrierungsBestätigung"),
				emailList);
	}

	final void sendCreationMail(final String eMailAddress,
			final String userName, final String password) {

		final ArrayList<EmailObject> emailList = this.createCreationMail(
				eMailAddress, userName, password);
		new EmailJob().sendMail(
				this.setEmailSettings("NSA - RegistrierungsBestätigung"),
				emailList);
	}

	private final ArrayList<EmailObject> createConfirmationMail(
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

	private ArrayList<EmailObject> createEmailObjects(final Timetable entityList) {

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

	private ArrayList<EmailObject> createCreationMail(final String userName,
			final String password, final String emailAddress) {

		final ArrayList<EmailObject> emailList = new ArrayList<EmailObject>();

		final EmailObject emailObject = new EmailObject();
		emailList.add(emailObject);

		final ArrayList<String> emailAddresList = emailObject
				.getEmailAddressList();

		final String emailText = new EmailTextCreator()
				.generateUserCreationText(userName, password);

		new EmailContentCreator().createMailContent(emailText, null,
				emailObject);

		emailAddresList.add(emailAddress);

		return emailList;
	}
}
