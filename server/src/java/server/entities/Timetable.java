package server.entities;

import java.util.List;

public class Timetable {

	private List<TimetableLesson> lessons;

	public List<TimetableLesson> getLessons() {
		return lessons;
	}

	public void setLessons(List<TimetableLesson> lessons) {
		this.lessons = lessons;
	}
}
