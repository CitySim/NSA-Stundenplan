package server.exceptions;

/**
 * Exception thrown if the login failed.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class LoginFailedException extends Exception implements ExceptionInterface {

    private final int errorNumber = 2;
    private final String errorTitel = "Login Exception";
    private final String errorMessage = "An error appeared while trying to login. UserName or password was incorrect.";

    private static final long serialVersionUID = -4565962119370664301L;

    public LoginFailedException() {
        super();
    }

    @Override
    public void showErrorMessage() {
        // TODO Auto-generated method stub

    }

}