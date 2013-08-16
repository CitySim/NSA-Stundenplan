package server.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EmailAddress extends DefaultEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	private String eMailAddress;
	
	public int getId() {
		return this.id;
	}

	public String getEMailAddress() {
		return this.eMailAddress;
	}

	public void setEMailAddress(final String eMailAddress) {
		this.eMailAddress = eMailAddress;
	}

}
