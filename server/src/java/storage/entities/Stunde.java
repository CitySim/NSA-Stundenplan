package storage.entities;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Stunde extends DefaultEntity {

	private Date zeitVon;
	private Date zeitBis;

	public Date getZeitVon() {
		return this.zeitVon;
	}

	public void setZeitVon(final Date zeitVon) {
		this.zeitVon = zeitVon;
	}

	public Date getZeitBis() {
		return this.zeitBis;
	}

	public void setZeitBis(final Date zeitBis) {
		this.zeitBis = zeitBis;
	}

}
