package server.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	@EmbeddedId
	@ManyToOne
	@JoinColumn(name = "idTag", insertable = false, updatable = false, nullable = false)
	private Day day;

	public Day getDay() {
		return day;
	}

	public void setDay(Day newDay) {
		day = newDay;
	}

	@EmbeddedId
	@ManyToOne
	@JoinColumn(name = "idStunde", insertable = false, updatable = false, nullable = false)
	private Lesson lesson;

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson newLesson) {
		lesson = newLesson;
	}

	@ManyToOne
	@JoinColumn(name = "idLehrer")
	private Teacher teacher;

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher newTeacher) {
		teacher = newTeacher;
	}

	@ManyToOne
	@JoinColumn(name = "idUnterrichtsfach")
	private Subject subject;

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject newSubject) {
		subject = newSubject;
	}

	@ManyToOne
	@JoinColumn(name = "idRaum")
	private Room room;

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room newRoom) {
		room = newRoom;
	}
	
	@OneToOne(targetEntity = Replacement.class)
	private Replacement replacement;

	public Replacement getReplacement() {
		return replacement;
	}

	public void setReplacement(Replacement replacement) {
		this.replacement = replacement;
	}
}