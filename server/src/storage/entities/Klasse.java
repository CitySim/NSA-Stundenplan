package storage.entities;

public class Klasse extends Entity {

	private Newsletter newsletter;
	private String bezeichung;

	public String getBezeichung() {
		return this.bezeichung;
	}

	public void setBezeichung(final String bezeichung) {
		this.bezeichung = bezeichung;
	}

	public Newsletter getNewsletter() {
		return this.newsletter;
	}

	public void setNewsletter(final Newsletter newsletter) {
		this.newsletter = newsletter;
	}
}
