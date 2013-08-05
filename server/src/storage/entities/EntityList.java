package storage.entities;

public class EntityList {

	private Klasse klasse;
	private Lehrer lehrer;
	private Login login;
	private Raum raum;
	private Stunde stunde;
	private Tag tag;
	private NewsLetter newsLetter;
	private Unterrichtsfach unterrichtsfach;
	private Vertretung vertretung;

	public Klasse getKlasse() {
		return this.klasse;
	}

	public void setKlasse(final Klasse klasse) {
		this.klasse = klasse;
	}

	public Lehrer getLehrer() {
		return this.lehrer;
	}

	public void setLehrer(final Lehrer lehrer) {
		this.lehrer = lehrer;
	}

	public Login getLogin() {
		return this.login;
	}

	public void setLogin(final Login login) {
		this.login = login;
	}

	public Raum getRaum() {
		return this.raum;
	}

	public void setRaum(final Raum raum) {
		this.raum = raum;
	}

	public Stunde getStunde() {
		return this.stunde;
	}

	public void setStunde(final Stunde stunde) {
		this.stunde = stunde;
	}

	public Tag getTag() {
		return this.tag;
	}

	public void setTag(final Tag tag) {
		this.tag = tag;
	}

	public Unterrichtsfach getUnterrichtsfach() {
		return this.unterrichtsfach;
	}

	public void setUnterrichtsfach(final Unterrichtsfach unterrichtsfach) {
		this.unterrichtsfach = unterrichtsfach;
	}

	public Vertretung getVertretung() {
		return this.vertretung;
	}

	public void setVertretung(final Vertretung vertretung) {
		this.vertretung = vertretung;
	}

	public NewsLetter getNewsLetter() {
		return this.newsLetter;
	}

	public void setNewsLetter(final NewsLetter newsLetter) {
		this.newsLetter = newsLetter;
	}
}
