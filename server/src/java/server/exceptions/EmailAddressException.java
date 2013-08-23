package server.exceptions;

import server.operations.ExceptionLogger;

/**
 * Exception thrown if a the E-Mail address is not valid.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class EmailAddressException extends SuperException {

	private static final int errorNumber = 5;
	private static final String errorTitel = "EmailAddress Exception";
	private static final String errorMessage = "An error appeared while trying to send out the E-Mail. The E-Mail address seems to be invalid.";

	private static final long serialVersionUID = -4565962119370664301L;

	public EmailAddressException() {
		super(errorNumber, errorTitel, errorMessage);
		new ExceptionLogger().logException(this);
	}
}
