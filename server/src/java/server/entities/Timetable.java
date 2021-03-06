package server.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Timetable extends DefaultEntity {

	private static final long serialVersionUID = 1L;

	@ManyToMany(cascade = CascadeType.ALL)
	private List<TimetableLesson> timetableLessons;

	@OneToOne(targetEntity = Form.class)
	private Form form;

	@OneToOne(targetEntity = Teacher.class)
	private Teacher teacher;

	@OneToOne(targetEntity = Room.class)
	private Room room;

	public List<TimetableLesson> getLessons() {
		return this.timetableLessons;
	}

	public void setLessons(final List<TimetableLesson> lessons) {
		this.timetableLessons = lessons;
	}

	public void addLesson(final TimetableLesson timetableLesson) {
		this.timetableLessons.add(timetableLesson);
	}

	public void removeTimetableLesson(final TimetableLesson timetableLesson) {
		this.timetableLessons.remove(timetableLesson);
	}

	public Form getForm() {
		return this.form;
	}

	public void setForm(final Form form) {
		this.form = form;
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
}