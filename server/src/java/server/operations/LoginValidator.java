package server.operations;

import server.exceptions.LoginFailedException;

/**
 * Class used validate the login as an Administrator.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class LoginValidator {

	public Boolean validateLoginData(final String userName,
			final String password) {

		final String dbPassword = "toDo";

		final Boolean passwordOkay = this
				.validatePassword(password, dbPassword);

		if (!passwordOkay) {
			new LoginFailedException().sendToClient();
		}

		return passwordOkay;

	}

	private final boolean validatePassword(final String password,
			final String dbPassword) {

		Boolean matches = false;

		if (BCrypt.checkpw(dbPassword, password)) {
			matches = true;
		}
		return matches;
	}

}
