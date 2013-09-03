package server.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Login extends DefaultEntity {

	private static final long serialVersionUID = 1L;
	private String user;
	private String password;
	@ManyToOne(targetEntity = EmailAddress.class)
	private EmailAddress email;

	public final String getUsername() {
		return this.user;
	}

	public final void setUser(final String user) {
		this.user = user;
	}

	public final String getPassword() {
		return this.password;
	}

	public final void setPassword(final String password) {
		this.password = password;
	}

	public final EmailAddress getEmail() {
		return this.email;
	}

	public final void setEmail(final EmailAddress email) {
		this.email = email;
	}
}