package storage.entities;

public class Login {

	private int idLogin;
	private String user;
	private String passwort;

	public int getIdLogin() {
		return this.idLogin;
	}

	public void setIdLogin(final int idLogin) {
		this.idLogin = idLogin;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(final String user) {
		this.user = user;
	}

	public String getPasswort() {
		return this.passwort;
	}

	public void setPasswort(final String passwort) {
		this.passwort = passwort;
	}

}
