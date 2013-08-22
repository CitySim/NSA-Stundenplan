package server.operations;

import server.entities.Login;
import server.exceptions.DuplicateUserException;
import server.exceptions.EmailSendingException;
import server.operations.email.EmailJobHelper;
import server.queries.LoginQuery;

/**
 * Used to create new administrator accounts on the database.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

class AccountHandler {

	final Login createAccount(final String name, final String familyName,
			final String eMailAddress) throws DuplicateUserException {

		final String userName = this.generateUserName(this.correctFormat(name),
				this.correctFormat(familyName));

		final String password = new PasswordEncryptor().generatePassword();

		final String hashedPw = new PasswordEncryptor()
				.encryptPassword(password);

		this.storeUserInDatabase(userName, hashedPw, eMailAddress);

		try {
			new EmailJobHelper().sendCreationMail(eMailAddress, userName,
					password);
		} catch (final EmailSendingException e) {
			new ExceptionLogger().logException(e);
		}

		// used for tests
		final Login login = new Login();
		login.setUser(userName);
		login.setPassword(password);

		return login;

	}

	final String changePassword(final String userName) {

		final String password = new PasswordEncryptor()
				.generateEncryptedPassword();

		this.changePasswordInDatabase(userName, password);

		// final TODO getEmailAddress for final user from DB
		// final String eMailAddress = "";
		// try {
		// new EmailJobHelper().sendPasswordChangeMail(eMailAddress, userName,
		// password);
		// } catch (final EmailSendingException e) {
		// new ExceptionLogger().logException(e);
		// }
		return password;
	}

	final boolean deleteAccount(final String userName) {

		return new LoginQuery().removeLogin(userName);
	}

	private void storeUserInDatabase(final String userName,
			final String hashedPw, final String eMailAddress)
			throws DuplicateUserException {

		final boolean success = new LoginQuery().createUser(userName, hashedPw,
				eMailAddress);

		if (!success) {
			final DuplicateUserException e = new DuplicateUserException();
			new ExceptionLogger().logException(e);
			throw e;
		}

	}

	private void changePasswordInDatabase(final String userName,
			final String hashedPw) {

		new LoginQuery().changePassword(userName, hashedPw);

	}

	private String generateUserName(final String name, final String familyName) {

		return familyName.substring(0, 4) + name.substring(0, 2) + "."
				+ this.getRndNumber();

	}

	private String getRndNumber() {

		return (int) (Math.random() * (10000 - 5000) + 300) + "";
	}

	private String correctFormat(String string) {
		string = string.trim();
		string = string.toLowerCase();
		string = string.substring(0, 1).toUpperCase() + string.substring(1);
		return string;
	}
}
