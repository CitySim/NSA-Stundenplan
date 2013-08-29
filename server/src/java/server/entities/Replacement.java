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
		return date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}
}