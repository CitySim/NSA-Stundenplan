package server.exceptions;

import server.operations.ExceptionLogger;

/**
 * Exception thrown if the cookie is invalid.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class CookieInvalidException extends SuperException {

	private static final int errorNumber = 8;
	private static final String errorTitel = "CookieInvalid Exception";
	private static final String errorMessage = "An error appeared while trying to fullfill the operation. Cookie was invalid.";

	private static final long serialVersionUID = -4565962119370664301L;

	public CookieInvalidException() {
		super(errorNumber, errorTitel, errorMessage);
		new ExceptionLogger().logException(this);
	}
}
