package server.persistence;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import server.operations.FilePrinterTest;

@RunWith(Suite.class)
@SuiteClasses({ HibernateTimeTableTest.class, FilePrinterTest.class, DataCreationTest.class })
public class PersistenceTestSuit {

}