package server.operations;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AccountHandlerTest.class, CookieHandlerTest.class, EmailSendingTest.class, ExceptionLoggerTest.class, FilePrinterTest.class,
		NewsLetterHandlerTest.class, PasswordValidatorTest.class })
public class OperationTestSuit {
}