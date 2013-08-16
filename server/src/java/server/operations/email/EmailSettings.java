package server.operations.email;

/**
 * E-Mail object containing the mail content and the list of addresses the mail
 * has to be send to.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class EmailSettings {

	private String username;
	private String password;
	private String senderAddress;
	private String subject;
	private String smtpHost;

	public EmailSettings(final String username, final String password,
			final String senderAddress, final String subject,
			final String smtpHost) {

		this.username = username;
		this.password = password;
		this.senderAddress = senderAddress;
		this.subject = subject;
		this.smtpHost = smtpHost;

	}

	public final String getUsername() {
		return this.username;
	}

	public final void setUsername(final String username) {
		this.username = username;
	}

	public final String getPassword() {
		return this.password;
	}

	public final void setPassword(final String password) {
		this.password = password;
	}

	public final String getSenderAddress() {
		return this.senderAddress;
	}

	public final void setSenderAddress(final String senderAddress) {
		this.senderAddress = senderAddress;
	}

	public final String getSubject() {
		return this.subject;
	}

	public final void setSubject(final String subject) {
		this.subject = subject;
	}

	public final String getSmtpHost() {
		return this.smtpHost;
	}

	public final void setSmtpHost(final String smtpHost) {
		this.smtpHost = smtpHost;
	}

}
