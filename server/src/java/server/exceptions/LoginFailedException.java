package server.exceptions;

import server.operations.ExceptionLogger;

/**
 * Exception thrown if the login failed.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class LoginFailedException extends SuperException {

	private static final int errorNumber = 2;
	private static final String errorTitel = "Login Exception";
	private static final String errorMessage = "An error appeared while trying to login. UserName or password was incorrect.";

	private static final long serialVersionUID = -4565962119370664301L;

	public LoginFailedException() {
		super(errorNumber, errorTitel, errorMessage);
		new ExceptionLogger().logException(this);
	}
}
