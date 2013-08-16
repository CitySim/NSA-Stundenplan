package server.operations;

import java.util.ArrayList;

import server.entities.Timetable;
import server.exceptions.EmailSendingException;
import server.exceptions.ScheduleCreationException;
import server.operations.email.EmailJob;
import server.operations.email.EmailObject;
import server.operations.email.EmailSettings;

/**
 * Used to initialize the emailSending.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

class EmailJobHelper {

	public final void sendMail(final Timetable entityList)
			throws ScheduleCreationException, EmailSendingException {
		final ArrayList<EmailObject> emailList = new EmailCreator()
				.createEmailObjects(entityList);
		new EmailJob().sendMail(
				this.setEmailSettings("NSA - Stundenplan Abweichung"),
				emailList);
	}

	final void sendConfirmationMail(final String eMailAddress,
			final String schoolClass) throws EmailSendingException {

		final ArrayList<EmailObject> emailList = new EmailCreator()
				.createConfirmationMail(eMailAddress, schoolClass);
		new EmailJob().sendMail(
				this.setEmailSettings("NSA - RegistrierungsBestätigung"),
				emailList);
	}

	final void sendCreationMail(final String eMailAddress,
			final String userName, final String password)
			throws EmailSendingException {

		final ArrayList<EmailObject> emailList = new EmailCreator()
				.createCreationMail(eMailAddress, userName, password);
		new EmailJob().sendMail(
				this.setEmailSettings("NSA - ErstellBestätigung"), emailList);
	}

	public void sendPasswordChangeMail(final String eMailAddress,
			final String userName, final String password)
			throws EmailSendingException {

		final ArrayList<EmailObject> emailList = new EmailCreator()
				.createPasswordChangeMail(eMailAddress, userName, password);
		new EmailJob().sendMail(
				this.setEmailSettings("NSA - PasswordÄnderungsBestätigung"),
				emailList);
	}

	private EmailSettings setEmailSettings(final String titel) {
		return new EmailSettings("nsa-stundenplan@gmx.de", "nsa-stundenplan",
				"nsa-stundenplan@gmx.de", titel, "smtp.gmx.net");
	}
}
