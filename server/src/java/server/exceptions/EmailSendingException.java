package server.exceptions;

/**
 * Exception thrown if a the E-Mail address is not valid.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class EmailSendingException extends SuperException {

	private final static int errorNumber = 6;
	private final static String errorTitel = "EmailSending Exception";
	private final static String errorMessage = "An error appeared while trying to send out the E-Mail. Please check your network and firewall settings.";

	private static final long serialVersionUID = -4565962119370664301L;

	public EmailSendingException() {
		super(errorNumber, errorTitel);
	}

}
