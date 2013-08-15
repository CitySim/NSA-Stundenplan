package server.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Klasse_Tag_Stunde")
public class TimetableLesson implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@ManyToOne
	@JoinColumn(name = "idKlasse", insertable = false, updatable = false, nullable = false)
	private Form form;

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	@EmbeddedId
	@ManyToOne
	@JoinColumn(name = "idTag", insertable = false, updatable = false, nullable = false)
	private Day tag;

	public Day getTag() {
		return tag;
	}

	public void setTag(Day tag) {
		this.tag = tag;
	}

	@EmbeddedId
	@ManyToOne
	@JoinColumn(name = "idStunde", insertable = false, updatable = false, nullable = false)
	private Lesson stunde;

	public Lesson getStunde() {
		return stunde;
	}

	public void setStunde(Lesson stunde) {
		this.stunde = stunde;
	}

	@ManyToOne
	@JoinColumn(name = "idLehrer", insertable = false, updatable = false, nullable = false)
	private Teacher lehrer;

	public Teacher getLehrer() {
		return lehrer;
	}

	public void setLehrer(Teacher lehrer) {
		this.lehrer = lehrer;
	}

	@ManyToOne
	@JoinColumn(name = "idUnterichtsfach", insertable = false, updatable = false, nullable = false)
	private Subject unterrichtsfach;

	public Subject getUnterrichtsfach() {
		return unterrichtsfach;
	}

	public void setUnterrichtsfach(Subject unterrichtsfach) {
		this.unterrichtsfach = unterrichtsfach;
	}

	@ManyToOne
	@JoinColumn(name = "idRaum", insertable = false, updatable = false, nullable = false)
	private Room raum;

	public Room getRaum() {
		return raum;
	}

	public void setRaum(Room raum) {
		this.raum = raum;
	}

	@ManyToOne
	@JoinColumn(name = "replacement_id", insertable = false, updatable = false, nullable = true)
	private Replacement replacement;

	public Replacement getReplacement() {
		return replacement;
	}

	public void setReplacement(Replacement replacement) {
		this.replacement = replacement;
	}

}
