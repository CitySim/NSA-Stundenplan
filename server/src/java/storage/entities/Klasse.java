package storage.entities;

import javax.persistence.Entity;

@Entity
public class Klasse extends DefaultEntity {

	private String bezeichung;

	public String getBezeichung() {
		return this.bezeichung;
	}

	public void setBezeichung(final String bezeichung) {
		this.bezeichung = bezeichung;
	}

}
