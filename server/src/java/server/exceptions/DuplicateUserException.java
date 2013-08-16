package server.exceptions;

/**
 * Exception thrown if a user is already existing.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class DuplicateUserException extends Exception implements
		ExceptionDialogInterface {

	int errorNumber = 4;
	String errorTitel = "DuplicateUser Exception";
	String errorMessage = "An error appeared while trying to create the user. A user with this name already exists.";

	private static final long serialVersionUID = -4565962119370664301L;

	public DuplicateUserException() {
		super();
	}

	@Override
	public void sendToClient() {
	}
}