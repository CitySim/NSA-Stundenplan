package server.exceptions;

/**
 * Exception thrown if the login failed.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class LoginFailedException extends SuperException {

	private final static int errorNumber = 2;
	private final static String errorTitel = "Login Exception";
	private final static String errorMessage = "An error appeared while trying to login. UserName or password was incorrect.";

	private static final long serialVersionUID = -4565962119370664301L;

	public LoginFailedException() {
		super(errorNumber, errorTitel, errorMessage);
	}

}