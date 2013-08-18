package server.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Login extends DefaultEntity {

	private static final long serialVersionUID = 1L;
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

	@ManyToOne(targetEntity=EmailAddress.class)
	private EmailAddress email;

	public EmailAddress getEmail() {
		return email;
	}

	public void setEmail(EmailAddress email) {
		this.email = email;
	}

}
