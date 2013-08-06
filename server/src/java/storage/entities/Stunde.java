package storage.entities;

import java.util.Date;

public class Stunde {

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
