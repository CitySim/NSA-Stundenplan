package server.entities;

import javax.persistence.Entity;

@Entity
public class Login extends DefaultEntity {

	private String user;
	private String password;

	public String getUser() {
		return this.user;
	}

	public void setUser(final String user) {
		this.user = user;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

}
