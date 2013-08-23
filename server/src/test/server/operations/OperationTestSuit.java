package server.operations;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Used to start all tests concerning the operations.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

@RunWith(Suite.class)
@SuiteClasses({ AccountHandlerTest.class, CookieHandlerTest.class, EmailSendingTest.class, ExceptionLoggerTest.class, FilePrinterTest.class,
		NewsLetterHandlerTest.class, PasswordValidatorTest.class })
public class OperationTestSuit {
}