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
		return timetableLessons;
	}

	public void setLessons(List<TimetableLesson> lessons) {
		this.timetableLessons = lessons;
	}

	// TODO Liste initialisieren
	public void addLesson(TimetableLesson timetableLesson) {
		timetableLessons.add(timetableLesson);
	}

	// TODO TESTEN
	public void removeTimetableLesson(TimetableLesson timetableLesson) {
		timetableLessons.remove(timetableLesson);
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
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
}
