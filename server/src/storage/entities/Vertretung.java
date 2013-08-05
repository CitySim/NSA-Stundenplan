package storage.entities;

import java.util.Date;

public class Vertretung {

	private int idVertretung;
	private Date datum;

	public int getIdVertretung() {
		return this.idVertretung;
	}

	public void setIdVertretung(final int idVertretung) {
		this.idVertretung = idVertretung;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(final Date datum) {
		this.datum = datum;
	}

}
