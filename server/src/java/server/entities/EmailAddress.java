package server.entities;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class EmailAddress extends DefaultEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String eMailAddress;

	public String getEMailAddress() {
		return this.eMailAddress;
	}

	public void setEMailAddress(final String eMailAddress) {
		this.eMailAddress = eMailAddress;
	}
}