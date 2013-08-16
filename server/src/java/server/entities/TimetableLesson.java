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
	@ManyToOne(targetEntity=Form.class)
	private Form form;

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	@EmbeddedId
	@ManyToOne(targetEntity=Day.class)
	private Day day;

	public Day getDay() {
		return day;
	}

	public void setDay(Day newDay) {
		day = newDay;
	}

	@EmbeddedId
	@ManyToOne(targetEntity=Lesson.class)
	private Lesson lesson;

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson newLesson) {
		lesson = newLesson;
	}

	@ManyToOne(targetEntity=Teacher.class)
	private Teacher teacher;

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher newTeacher) {
		teacher = newTeacher;
	}

	@ManyToOne(targetEntity=Subject.class)
	private Subject subject;

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject newSubject) {
		subject = newSubject;
	}

	@ManyToOne(targetEntity=Room.class)
	private Room room;

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room newRoom) {
		room = newRoom;
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
