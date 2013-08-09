package server.entities;

import javax.persistence.Entity;

@Entity
public class Login extends DefaultEntity {

	private String user;
	private String passwort;

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
