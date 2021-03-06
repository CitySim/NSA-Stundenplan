package server.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Replacement extends DefaultEntity {

	private static final long serialVersionUID = 1L;

	// format: "2013-W31"
	private String week;

	@ManyToOne(targetEntity = Form.class)
	private Form form;
	
	@ManyToOne(targetEntity = Day.class)
	private Day day;

	@ManyToOne(targetEntity = Lesson.class)
	private Lesson lesson;
	
	@ManyToOne(targetEntity = Teacher.class)
	private Teacher teacher;

	@ManyToOne(targetEntity = Teacher.class)
	private Teacher oldteacher;

	@ManyToOne(targetEntity = Room.class)
	private Room room;

	@ManyToOne(targetEntity = Subject.class)
	private Subject subject;

	private String note;

	private int cancel;
	
	public String getWeek() {
		return this.week;
	}

	public void setWeek(final String week) {
		this.week = week;
	}
	
	public Form getForm() {
		return this.form;
	}

	public void setForm(final Form form) {
		this.form = form;
	}
	
	public Day getDay() {
		return this.day;
	}

	public void setDay(Day day) {
		this.day = day;
	}

	public Lesson getLesson() {
		return this.lesson;
	}

	public void setLesson(final Lesson lesson) {
		this.lesson = lesson;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(final Teacher teacher) {
		this.teacher = teacher;
	}

	public Teacher getOldteacher() {
		return oldteacher;
	}

	public void setOldteacher(final Teacher oldteacher) {
		this.oldteacher = oldteacher;
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

	public int getCancel() {
		return cancel;
	}

	public void setCancel(int cancel) {
		this.cancel = cancel;
	}
	
}