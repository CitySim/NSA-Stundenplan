package server.operations;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import server.exceptions.SuperException;

/**
 * Test for exception logging.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class ExceptionLoggerTest extends TestCase {

	private ExceptionLogger logger;

	@Override
	@Before
	public void setUp() {
		this.logger = new ExceptionLogger();
	}

	@Test
	public void testExceptionLogging() {
		ExceptionLoggerTest.assertFalse(this.logger.logException(new SuperException(0, "TestException", "JustTesting")));
	}
}
