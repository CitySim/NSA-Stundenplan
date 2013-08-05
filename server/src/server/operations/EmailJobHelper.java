package server.operations;

import java.util.ArrayList;

import storage.entities.EntityList;
import dennis.markmann.MyLibraries.DefaultJobs.Email.EmailJob;
import dennis.markmann.MyLibraries.DefaultJobs.Email.EmailObject;
import dennis.markmann.MyLibraries.DefaultJobs.Email.EmailSettings;

/**
 * Helper class used to initialize the emailSending and to create the emails to
 * send.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_21
 * @version 1.0
 */

public class EmailJobHelper {

	private EmailSettings setEmailSettings() {
		return new EmailSettings("nsa-stundenplan@gmx.de", "nsa-stundenplan",
				"nsa-stundenplan@gmx.de", "NSA - Stundenplan Abweichung",
				"smtp.gmx.net");
	}

	public final void sendMailToGroups(final EntityList entityList) {

		final ArrayList<EmailObject> emailList = this
				.createEmailObjects(entityList);
		new EmailJob().sendMail(this.setEmailSettings(), emailList);
	}

	private ArrayList<EmailObject> createEmailObjects(
			final EntityList entityList) {

		final ArrayList<EmailObject> emailList = new ArrayList<EmailObject>();

		// for (final NewsLetter newsLetter : entityList) {

		// final EmailObject emailObject = new EmailObject();
		// emailList.add(emailObject);
		//
		// final ArrayList<String> emailAddresList = emailObject
		// .getEmailAddressList();
		// final String emailText = new
		// TextCreator().generateMailText(group,
		// path);
		//
		// new EmailContentCreator().createMailContent(emailText, file,
		// emailObject);
		//
		// for (final Member member : group.getMemberList()) {
		// emailAddresList.add(member.getEMailAdress());
		// }
		// }
		return emailList;
	}
}
