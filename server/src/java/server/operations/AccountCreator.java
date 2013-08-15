package server.operations;

import server.queries.LoginQuery;

/**
 * Class used to create new administrator accounts on the database.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class AccountCreator {

	public final String createAccount(final String name,
			final String familyName, final String eMailAddress) {

		final String userName = this.generateUserName(this.correctFormat(name),
				this.correctFormat(familyName));

		final String password = new PasswordEncryptor()
				.generateEncryptedPassword();

		this.storeUserInDatabase(userName, password);

		new EmailJobHelper().sendCreationMail(eMailAddress, userName, password);

		return userName;

	}

	public String changePassword(final String userName) {

		final String password = new PasswordEncryptor()
				.generateEncryptedPassword();

		this.changePasswordInDatabase(userName, password);

		// TODO getEmailAddress for user from DB
		final String eMailAddress = "";

		new EmailJobHelper().sendPasswordChangeMail(eMailAddress, userName,
				password);

		return password;
	}

	private void storeUserInDatabase(final String userName,
			final String hashedPw) {

		new LoginQuery().createUser(userName, hashedPw);

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
