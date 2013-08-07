package storage.entities;

import javax.persistence.Entity;

@Entity
public class Raum extends DefaultEntity {

	private String bezeichnung;

	public String getBezeichnung() {
		return this.bezeichnung;
	}

	public void setBezeichnung(final String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

}
