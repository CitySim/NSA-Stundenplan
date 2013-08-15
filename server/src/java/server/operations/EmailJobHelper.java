package server.operations;

import java.util.ArrayList;

import server.entities.Timetable;
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
		final ArrayList<EmailObject> emailList = new EmailCreator()
				.createEmailObjects(entityList);
		new EmailJob().sendMail(
				this.setEmailSettings("NSA - Stundenplan Abweichung"),
				emailList);
	}

	final void sendConfirmationMail(final String eMailAddress,
			final String schoolClass) {

		final ArrayList<EmailObject> emailList = new EmailCreator()
				.createConfirmationMail(eMailAddress, schoolClass);
		new EmailJob().sendMail(
				this.setEmailSettings("NSA - RegistrierungsBestätigung"),
				emailList);
	}

	final void sendCreationMail(final String eMailAddress,
			final String userName, final String password) {

		final ArrayList<EmailObject> emailList = new EmailCreator()
				.createCreationMail(eMailAddress, userName, password);
		new EmailJob().sendMail(
				this.setEmailSettings("NSA - ErstellBestätigung"), emailList);
	}

	public void sendPasswordChangeMail(final String eMailAddress,
			final String userName, final String password) {

		final ArrayList<EmailObject> emailList = new EmailCreator()
				.createPasswordChangeMail(eMailAddress, userName, password);
		new EmailJob().sendMail(
				this.setEmailSettings("NSA - PasswordÄnderungsBestätigung"),
				emailList);
	}

}
