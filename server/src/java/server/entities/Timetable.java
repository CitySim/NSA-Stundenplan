package server.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Timetable extends DefaultEntity {

	private static final long serialVersionUID = 1L;

	@ManyToMany(cascade = CascadeType.ALL)
	private List<TimetableLesson> timetableLessons;

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
}
