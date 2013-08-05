package storage.entities;

public class Unterrichtsfach {

	private int idUnterrichtsfach;
	private String kurzName;
	private String bezeichnung;

	public int getIdUnterrichtsfach() {
		return this.idUnterrichtsfach;
	}

	public void setIdUnterrichtsfach(final int idUnterrichtsfach) {
		this.idUnterrichtsfach = idUnterrichtsfach;
	}

	public String getKurzName() {
		return this.kurzName;
	}

	public void setKurzName(final String kurzName) {
		this.kurzName = kurzName;
	}

	public String getBezeichnung() {
		return this.bezeichnung;
	}

	public void setBezeichnung(final String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

}
