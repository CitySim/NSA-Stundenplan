package server.operations.test;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.exceptions.ScheduleCreationException;
import server.operations.CacheCleaner;
import server.operations.FilePrinter;

/**
 * Test for cookie creation and validation.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class FilePrinterTest extends TestCase {

	private FilePrinter printer;
	private CacheCleaner cleaner;

	@Override
	@Before
	public void setUp() throws Exception {
		this.printer = new FilePrinter();
		this.cleaner = new CacheCleaner();
	}

	@Test
	public void testprintAsPDF() {
		try {
			FilePrinterTest.assertNotNull(this.printer.printAsPDF());
		} catch (final ScheduleCreationException e) {
			FilePrinterTest.fail();
		}
	}

	@Test
	public void testprintAsPNG() {
		try {
			FilePrinterTest.assertNotNull(this.printer.printAsPng());
		} catch (final ScheduleCreationException e) {
			FilePrinterTest.fail();
		}
	}

	@After
	public void cleanUpTestData() {
		this.cleaner.cleanCache();
	}
}
