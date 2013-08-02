package server.operations;

/**
 * Class for generating the default text used for output operations. (email &
 * print)
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_21
 * @version 1.0
 */

class TextCreator {

	String createGroupText() {

		final StringBuilder sb = new StringBuilder();

		sb.append("Hallo,");
		sb.append(System.lineSeparator());
		sb.append("für den");
		// Tag + "den" +DATUM
		// hat sich eine Abweichung ergeben.

		// Betroffene Stunde(n) [Font größer]
		// Stunde | Fach | Lehrer

		return sb.toString();
	}
	//
	// String generateMailText(final Group group, final String path) {
	//
	// final StringBuilder sb = new StringBuilder();
	//
	// sb.append("Hello! ");
	// sb.append(System.lineSeparator());
	// sb.append("This is an autmatic generated mail by the groupBuilder of Dennis Markmann.");
	// sb.append(System.lineSeparator());
	// sb.append(System.lineSeparator());
	// sb.append(new TextCreator().createGroupText(group));
	//
	// return sb.toString();
	// }

}
