package server.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import server.operations.AccountHandlerTest;
import server.operations.CookieHandlerTest;
import server.operations.ExceptionLoggerTest;
import server.operations.FilePrinterTest;
import server.operations.NewsLetterHandlerTest;
import server.operations.PasswordValidatorTest;
import server.persistence.DataCreationTest;
import server.persistence.HibernateTimeTableTest;
import server.persistence.PersistEntityTest;

@RunWith(Suite.class)
@SuiteClasses({ DataCreationTest.class, AccountHandlerTest.class, CookieHandlerTest.class, ExceptionLoggerTest.class, FilePrinterTest.class,
		NewsLetterHandlerTest.class, PasswordValidatorTest.class, HibernateTimeTableTest.class, PersistEntityTest.class })
public class AllTests {

}