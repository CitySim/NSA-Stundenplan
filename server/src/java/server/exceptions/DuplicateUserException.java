package server.exceptions;

import server.operations.ExceptionLogger;

/**
 * Exception thrown if a user is already existing.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class DuplicateUserException extends SuperException {

	private static final int errorNumber = 4;
	private static final String errorTitel = "DuplicateUser Exception";
	private static final String errorMessage = "An error appeared while trying to create the user. A user with this name already exists.";

	private static final long serialVersionUID = -4565962119370664301L;

	public DuplicateUserException() {
		super(errorNumber, errorTitel, errorMessage);
		new ExceptionLogger().logException(this);
	}
}
