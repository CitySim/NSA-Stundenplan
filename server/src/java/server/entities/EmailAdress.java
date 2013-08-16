package server.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EmailAdress {

	@Id
	@GeneratedValue
	@Column(name = "idEmail")
	private int id;

	@Column(name = "email")
	private String eMailAddress;

	public String getEmailAddress() {
		return this.eMailAddress;
	}

	public void setEmailAddress(final String eMailAddress) {
		this.eMailAddress = eMailAddress;
	}

	public int getId() {
		return this.id;
	}
}
