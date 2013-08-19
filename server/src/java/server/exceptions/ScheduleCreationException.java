package server.exceptions;

/**
 * Exception thrown if the schedule can't be created.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class ScheduleCreationException extends SuperException {

	private final static int errorNumber = 1;
	private final static String errorTitel = "Schedule Creation Exception";
	private final static String errorMessage = "An error appeared while creating the schedule. Please try again later.";

	private static final long serialVersionUID = -4565962119370664301L;

	public ScheduleCreationException() {
		super(errorNumber, errorTitel, errorMessage);
	}

}
