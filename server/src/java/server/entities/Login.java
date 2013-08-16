package server.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Login extends DefaultEntity {
	
	@Id
	@GeneratedValue
	@Column(name = "idLogin")
	private int id;

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

	@ManyToOne(targetEntity=EmailAdress.class)
	private EmailAdress email;

	public EmailAdress getEmail() {
		return email;
	}

	public void setEmail(EmailAdress email) {
		this.email = email;
	}

}
