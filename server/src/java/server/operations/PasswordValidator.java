package server.operations;

/**
 * Class for generating the text for the schedule mails.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class PasswordValidator {

	public boolean validatePassword(final String password) {

		Boolean matches = false;

		// TODO "password" durch einen Check für das zum User gehörige Password
		// ersetzen
		if (BCrypt.checkpw("password", password)) {
			matches = true;
		}
		return matches;
	}

	private String getPasswordFromDB() {

		final String password = null;

		// TODO password aus der DB ziehen

		return password;

	}
}
