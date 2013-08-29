package server.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import server.operations.OperationTestSuit;
import server.persistence.DataCreationTest;

/**
 * Used to start all tests for the application.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

@RunWith(Suite.class)
@SuiteClasses({ DataCreationTest.class, OperationTestSuit.class })
public class AllTests {
}