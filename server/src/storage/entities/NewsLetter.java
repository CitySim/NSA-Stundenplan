package storage.entities;

public class NewsLetter {

	private int idNewsLetter;
	private int klassenID;
	private String emailAddresse;

	public int getIdNewsLetter() {
		return this.idNewsLetter;
	}

	public void setIdNewsLetter(final int idNewsLetter) {
		this.idNewsLetter = idNewsLetter;
	}

	public int getKlassenID() {
		return this.klassenID;
	}

	public void setKlassenID(final int klassenID) {
		this.klassenID = klassenID;
	}

	public String getEmailAddresse() {
		return this.emailAddresse;
	}

	public void setEmailAddresse(final String emailAddresse) {
		this.emailAddresse = emailAddresse;
	}

}
