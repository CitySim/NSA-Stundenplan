package storage.entities;

public class Lehrer {

	private int idLehrer;
	private String kurzName;
	private String name;
	private String vorname;

	public int getIdLehrer() {
		return this.idLehrer;
	}

	public void setIdLehrer(final int idLehrer) {
		this.idLehrer = idLehrer;
	}

	public String getKurzName() {
		return this.kurzName;
	}

	public void setKurzName(final String kurzName) {
		this.kurzName = kurzName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getVorname() {
		return this.vorname;
	}

	public void setVorname(final String vorname) {
		this.vorname = vorname;
	}

}
