package storage.entities;

import java.util.Date;

public class Stunde {

	private int idStunde;
	private Date zeitVon;
	private Date zeitBis;

	public int getIdStunde() {
		return this.idStunde;
	}

	public void setIdStunde(final int idStunde) {
		this.idStunde = idStunde;
	}

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
