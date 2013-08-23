package server.operations.email;

import java.util.ArrayList;

import javax.mail.internet.MimeMultipart;

/**
 * E-Mail object containing the mail content and the list of addresses the mail
 * has to be send to.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

class EmailObject {

	private MimeMultipart mailContent;
	private final ArrayList<String> emailAddressList = new ArrayList<>();

	public final MimeMultipart getMailContent() {
		return this.mailContent;
	}

	public final void setMailContent(final MimeMultipart mailContent) {
		this.mailContent = mailContent;
	}

	public final ArrayList<String> getEmailAddressList() {
		return this.emailAddressList;
	}

}
