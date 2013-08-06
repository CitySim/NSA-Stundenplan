package storage.entities;

public class Klasse extends DefaultEntity {

	private static final long serialVersionUID = -2242235990354949800L;
	
	private String bezeichung;

	public String getBezeichung() {
		return this.bezeichung;
	}

	public void setBezeichung(final String bezeichung) {
		this.bezeichung = bezeichung;
	}

}
