package server.operations;

import server.exceptions.LoginFailedException;
import server.queries.LoginQuery;

/**
 * Used to validate the login as an Administrator.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class LoginValidator {

	public final Boolean validateLoginData(final String userName,
			final String password) throws LoginFailedException {

		final String dbPassword = new LoginQuery().getPassword(userName);

		if (dbPassword == null) {
			final LoginFailedException e = new LoginFailedException();
			new ExceptionLogger().logException(e);
			throw e;
		}

		final Boolean passwordOkay = this
				.validatePassword(password, dbPassword);

		if (!passwordOkay) {
			final LoginFailedException e = new LoginFailedException();
			new ExceptionLogger().logException(e);
			throw e;
		}

		return passwordOkay;

	}

	private boolean validatePassword(final String password,
			final String dbPassword) {

		Boolean matches = false;

		if (BCrypt.checkpw(password, dbPassword)) {
			matches = true;
		}
		return matches;
	}

}
