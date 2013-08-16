package server.exceptions;

/**
 * Exception thrown if a the E-Mail address is not valid.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class EmailAddressException extends Exception {

	int errorNumber = 5;
	String errorTitel = "EmailAddress Exception";
	String errorMessage = "An error appeared while trying to send out the E-Mail. The E-Mail address seems to be invalid.";

	private static final long serialVersionUID = -4565962119370664301L;

	public EmailAddressException() {
		super();
	}

}
