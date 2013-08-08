package storage.entities;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Vertretung extends DefaultEntity {

	private Date datum;

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(final Date datum) {
		this.datum = datum;
	}

}
