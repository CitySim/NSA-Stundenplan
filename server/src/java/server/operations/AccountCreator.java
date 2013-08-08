package server.operations;

public class AccountCreator {

	public static void main(final String[] args) {

		new AccountCreator().createAccount("Dennis", "Markmann",
				new PasswordEncryptor().generateEncryptedPassword());

	}

	public void createAccount(final String name, final String familyName,
			final String password) {

		final String userName = this.generateUserName(this.correctFormat(name),
				this.correctFormat(familyName));

		this.storeUserInDatabase(userName, password);

	}

	private void storeUserInDatabase(final String userName,
			final String hashedPw) {

		// TODO create user in DB
		System.out.println(userName);
		System.out.println(hashedPw);

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
