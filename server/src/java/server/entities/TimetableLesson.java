package server.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "klasse_tag_stunde")
public class TimetableLesson implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@ManyToOne
	@JoinColumn(name = "idKlasse", insertable = false, updatable = false, nullable = false)
	private Form form;

	public Form getForm() {
		return this.form;
	}

	public final void setForm(final Form form) {
		this.form = form;
	}

	@EmbeddedId
	@ManyToOne
	@JoinColumn(name = "idTag", insertable = false, updatable = false, nullable = false)
	private Day day;

	public Day getDay() {
		return this.day;
	}

	public void setDay(final Day newDay) {
		this.day = newDay;
	}

	@EmbeddedId
	@ManyToOne
	@JoinColumn(name = "idStunde", insertable = false, updatable = false, nullable = false)
	private Lesson lesson;

	public Lesson getLesson() {
		return this.lesson;
	}

	public void setLesson(final Lesson newLesson) {
		this.lesson = newLesson;
	}

	@ManyToOne
	@JoinColumn(name = "idLehrer")
	private Teacher teacher;

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(final Teacher newTeacher) {
		this.teacher = newTeacher;
	}

	@ManyToOne
	@JoinColumn(name = "idUnterrichtsfach")
	private Subject subject;

	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(final Subject newSubject) {
		this.subject = newSubject;
	}

	@ManyToOne
	@JoinColumn(name = "idRaum")
	private Room room;

	public Room getRoom() {
		return this.room;
	}

	public void setRoom(final Room newRoom) {
		this.room = newRoom;
	}
}