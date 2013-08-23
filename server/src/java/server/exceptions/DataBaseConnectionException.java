package server.exceptions;

import server.operations.ExceptionLogger;

/**
 * Exception thrown if the connection to the database fails.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class DataBaseConnectionException extends SuperException {

	private static final int errorNumber = 3;
	private static final String errorTitel = "DataBaseConnection Exception";
	private static final String errorMessage = "An error appeared while connecting to the database. Please check your network settings and firewall.";

	private static final long serialVersionUID = -4565962119370664301L;

	public DataBaseConnectionException() {
		super(errorNumber, errorTitel, errorMessage);
		new ExceptionLogger().logException(this);
	}
}