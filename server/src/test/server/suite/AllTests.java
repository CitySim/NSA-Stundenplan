package server.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import server.operations.OperationTestSuit;
import server.persistence.PersistenceTestSuit;

@RunWith(Suite.class)
@SuiteClasses({ OperationTestSuit.class, PersistenceTestSuit.class })
public class AllTests {

}