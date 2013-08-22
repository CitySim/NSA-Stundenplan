package server.exceptions;

/**
 * Exception thrown if a the parsing process fails.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class ParsingException extends SuperException {

	private static final int errorNumber = 7;
	private static final String errorTitel = "Parsing Exception";
	private static final String errorMessage = "An error appeared while trying to parse a String to Date.";

	private static final long serialVersionUID = -4565962119370664301L;

	public ParsingException() {
		super(errorNumber, errorTitel, errorMessage);
	}

}