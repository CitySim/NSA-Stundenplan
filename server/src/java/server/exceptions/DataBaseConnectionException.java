package server.exceptions;

/**
 * Exception thrown if the connection to the database fails.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class DataBaseConnectionException extends Exception implements ExceptionInterface {

    private final int errorNumber = 3;
    private final String errorTitel = "DataBaseConnection Exception";
    private final String errorMessage = "An error appeared while connecting to the database. Please check your network settings and firewall.";

    private static final long serialVersionUID = -4565962119370664301L;

    public DataBaseConnectionException() {
        super();
    }

    @Override
    public void showErrorMessage() {
        // TODO Auto-generated method stub

    }
}