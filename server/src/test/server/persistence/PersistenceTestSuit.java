package server.persistence;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import server.operations.FilePrinterTest;

/**
 * Used to start all tests concerning the persistence.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

@RunWith(Suite.class)
@SuiteClasses({ HibernateTimeTableTest.class, FilePrinterTest.class, DataCreationTest.class })
public class PersistenceTestSuit {

}