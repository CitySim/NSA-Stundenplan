package server.entities;

import javax.persistence.Entity;

@Entity
public class EmailAdress extends DefaultEntity {

	private String eMailAddress;

	public String geteMailAddress() {
		return this.eMailAddress;
	}

	public void seteMailAddress(final String eMailAddress) {
		this.eMailAddress = eMailAddress;
	}

}