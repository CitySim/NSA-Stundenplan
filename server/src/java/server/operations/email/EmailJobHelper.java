package server.operations.email;

import java.util.ArrayList;

import server.entities.Form;
import server.entities.Login;
import server.entities.Newsletter;
import server.entities.Replacement;
import server.exceptions.EmailAddressException;
import server.exceptions.EmailSendingException;

/**
 * Used to initialize the emailSending.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class EmailJobHelper {

	public final void sendNewsLetterMail(final Replacement replacement) throws EmailSendingException,
			EmailAddressException {
		final ArrayList<EmailObject> emailList = new EmailCreator().createNewsLetterMails(replacement);
		new EmailJob().sendMail(this.setEmailSettings("NSA - Stundenplan Abweichung"), emailList);
	}

	public final void sendConfirmationMail(final Form form, final String email) throws EmailSendingException, EmailAddressException {

		final ArrayList<EmailObject> emailList = new EmailCreator().createConfirmationMail(form, email);
		new EmailJob().sendMail(this.setEmailSettings("NSA - Registrierungs Bestätigung"), emailList);
	}

	public final void sendCreationMail(final String eMailAddress, final String userName, final String password) throws EmailSendingException,
			EmailAddressException {

		final ArrayList<EmailObject> emailList = new EmailCreator().createCreationMail(userName, password, eMailAddress);
		new EmailJob().sendMail(this.setEmailSettings("NSA - Erstell Bestätigung"), emailList);
	}
	
	public final void sendResetPasswordMail(final Login login) throws EmailSendingException, EmailAddressException {
		
		final ArrayList<EmailObject> emailList = new EmailCreator().createResetPasswordMail(login);
		new EmailJob().sendMail(this.setEmailSettings("NSA - Password zurücksetzen"), emailList);
	}

	public final void sendPasswordChangedMail(final Login login, final String password) throws EmailSendingException,
			EmailAddressException {

		final ArrayList<EmailObject> emailList = new EmailCreator().createPasswordChangedMail(login, password);
		new EmailJob().sendMail(this.setEmailSettings("NSA - Password Änderungs Bestätigung"), emailList);
	}
	
	public boolean sendRemoveRegistrationMail(final Newsletter newsletter) throws EmailSendingException, EmailAddressException {
		final ArrayList<EmailObject> emailList = new EmailCreator().createRemoveRegistrationMail(newsletter);
		new EmailJob().sendMail(this.setEmailSettings("NSA - Newsletter abmelden"), emailList);
		return true;
	}

	private EmailSettings setEmailSettings(final String titel) {
		return new EmailSettings("postmaster", "", "postmaster@localhost", titel, "localhost");
	}
}