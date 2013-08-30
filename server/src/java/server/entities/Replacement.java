package server.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Replacement extends DefaultEntity {

	private static final long serialVersionUID = 1L;

	private Date date;

	@ManyToOne(targetEntity = Teacher.class)
	private Teacher teacher;

	@ManyToOne(targetEntity = Room.class)
	private Room room;

	@ManyToOne(targetEntity = Subject.class)
	private Subject subject;

	@ManyToOne(targetEntity = Form.class)
	private Form form;

	@ManyToOne(targetEntity = Lesson.class)
	private Lesson lesson;

	private String note;

	public Date getDate() {
		return this.date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(final Teacher teacher) {
		this.teacher = teacher;
	}

	public Room getRoom() {
		return this.room;
	}

	public void setRoom(final Room room) {
		this.room = room;
	}

	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(final Subject subject) {
		this.subject = subject;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(final String note) {
		this.note = note;
	}

	public Form getForm() {
		return this.form;
	}

	public void setForm(final Form form) {
		this.form = form;
	}

	public Lesson getLesson() {
		return this.lesson;
	}

	public void setLesson(final Lesson lesson) {
		this.lesson = lesson;
	}
}