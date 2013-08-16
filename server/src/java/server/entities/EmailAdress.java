package server.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EmailAdress extends DefaultEntity {

	@Id
	@GeneratedValue
	@Column(name = "idEmail")
	private int id;

	@Column(name = "Email")
	private String eMailAddress;

	
	public int getId() {
		return this.id;
	}

	public String getEmailAddress() {
		return this.eMailAddress;
	}

	public void setEmailAddress(final String eMailAddress) {
		this.eMailAddress = eMailAddress;
	}

}
