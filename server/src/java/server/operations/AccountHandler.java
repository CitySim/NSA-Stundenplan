package server.operations;

import server.entities.Login;
import server.exceptions.DuplicateUserException;
import server.exceptions.EmailAddressException;
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

public class AccountHandler {
	
	public static final String URL_PREFIX = "http://localhost:8080/server/";

	final Login createAccount(final String name, final String familyName, final String eMailAddress) throws DuplicateUserException,
			EmailSendingException, EmailAddressException {

		final String userName = this.generateUserName(this.correctFormat(name), this.correctFormat(familyName));

		final String password = new PasswordEncryptor().generatePassword();

		final String hashedPw = new PasswordEncryptor().encryptPassword(password);

		this.storeUserInDatabase(userName, hashedPw, eMailAddress);

		new EmailJobHelper().sendCreationMail(eMailAddress, userName, password);

		// used for tests
		final Login login = new Login();
		login.setUser(userName);
		login.setPassword(password);

		return login;

	}
	
	public final void resetPassword(final String userName) throws EmailSendingException, EmailAddressException {
		
		Login login = new LoginQuery().getLogin(userName);	
		new EmailJobHelper().sendResetPasswordMail(login);		
	}
	
	public Object generateResetPasswordLink(int loginId) {
		return URL_PREFIX + "login/resetpw?user=" + loginId;
	}

	public final String changePassword(final String userName) throws EmailSendingException, EmailAddressException {
		
		Login login = new LoginQuery().getLogin(userName);

		final PasswordEncryptor encryptor = new PasswordEncryptor();

		final String password = encryptor.generatePassword();

		this.changePasswordInDatabase(login, encryptor.encryptPassword(password));

		new EmailJobHelper().sendPasswordChangedMail(login, password);

		return password;
	}

	private void changePasswordInDatabase(final Login login, final String hashedPw) {
		
		new LoginQuery().changePassword(login, hashedPw);
		
	}
	
	protected final boolean deleteAccount(final String userName) {

		return new LoginQuery().removeLogin(userName);
	}

	private void storeUserInDatabase(final String userName, final String hashedPw, final String eMailAddress) throws DuplicateUserException {

		final boolean success = new LoginQuery().createUser(userName, hashedPw, eMailAddress);

		if (!success) {
			throw new DuplicateUserException();
		}

	}

	private String generateUserName(final String name, final String familyName) {

		return familyName.substring(0, 4) + name.substring(0, 2) + "." + this.getRndNumber();

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