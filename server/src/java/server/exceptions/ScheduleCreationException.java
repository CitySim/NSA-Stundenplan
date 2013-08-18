package server.exceptions;

/**
 * Exception thrown if the schedule can't be created.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class ScheduleCreationException extends Exception implements ExceptionInterface {

    private final int errorNumber = 1;
    private final String errorTitel = "Schedule Creation Exception";
    private final String errorMessage = "An error appeared while creating the schedule. Please try again later.";

    private static final long serialVersionUID = -4565962119370664301L;

    public ScheduleCreationException() {
        super();
    }

    @Override
    public void showErrorMessage() {
        // TODO Auto-generated method stub

    }

}
