package server.persistence;

import static org.junit.Assert.assertTrue;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.entities.Day;
import server.entities.Form;
import server.entities.Lesson;
import server.entities.Room;
import server.entities.Subject;
import server.entities.Teacher;
import server.entities.Timetable;
import server.entities.TimetableLesson;

public class DataCreationTest {

	private EntityManager em;

	@Before
	public void init() {
		this.em = HibernateUtil.getEntityManager();
	}

	@After
	public void cleanup() {
		this.em.close();
	}

	@Test
	public void entityManagerTest() {

		this.em.getTransaction().begin();

		final Teacher lürssen = this.createTeacher("Volker", "Lürssen", "Lü");
		final Teacher albers = this.createTeacher("Kirsten", "Albers", "Al");
		final Teacher herrmann = this.createTeacher("Werner", "Herrmann", "Hr");
		final Teacher bastians = this.createTeacher("Thomas", "Bastians", "Ba");
		final Teacher giera = this.createTeacher("Heike", "Giera", "Ga");
		final Teacher wehmeyer = this.createTeacher("Markus", "Wehmeyer", "Wm");
		final Teacher burg = this.createTeacher("Burg", "Burg", "Bu");

		final Form it1a = this.createForm("it1a", herrmann);
		final Form it1b = this.createForm("it1b", lürssen);

		final Room raum32 = this.createRoom("raum32");
		final Room raum53 = this.createRoom("raum53");
		final Room raum81 = this.createRoom("raum81");
		final Room raum82 = this.createRoom("raum82");
		final Room raum114 = this.createRoom("raum144");

		final Lesson lesson1 = this.createLesson("07:45:00", "08:30:00");
		final Lesson lesson2 = this.createLesson("08:30:00", "09:15:00");
		final Lesson lesson3 = this.createLesson("09:30:00", "10:15:00");
		final Lesson lesson4 = this.createLesson("10:15:00", "11:00:00");
		final Lesson lesson5 = this.createLesson("11:30:00", "12:15:00");
		final Lesson lesson6 = this.createLesson("12:15:00", "13:00:00");
		final Lesson lesson7 = this.createLesson("13:15:00", "14:00:00");
		final Lesson lesson8 = this.createLesson("14:00:00", "14:45:00");
		final Lesson lesson9 = this.createLesson("15:00:00", "15:45:00");
		final Lesson lesson10 = this.createLesson("15:45:00", "16:30:00");

		final Subject ae = this.createSubject("Anwendungsentwicklung", "AE");
		final Subject suk = this.createSubject("Sprache und Kommunikation",
				"SuK");
		final Subject itSyst = this.createSubject("IT-Systeme", "IT-Syst");
		final Subject wug = this.createSubject("Wirtschaft und Gesellschaft",
				"WuG");
		final Subject orgaGp = this.createSubject(
				"Organisation und Geschäftsprozesse", "OrgaGp");
		final Subject fe = this.createSubject("Fachenglisch", "FE");
		final Subject pro = this.createSubject("Projekt", "Pro");

		final Day montag = this.createDay("Montag");
		final Day dienstag = this.createDay("Diestag");
		final Day mittwoch = this.createDay("Mittwoch");
		final Day donnerstag = this.createDay("Donnerstag");
		final Day freitag = this.createDay("Freitag");

		final List<TimetableLesson> timetableLessons = new ArrayList<TimetableLesson>();

		// Lessons for Monday
		this.createTimeTableSession(it1a, lürssen, raum53, lesson3, montag, ae,
				timetableLessons);
		this.createTimeTableSession(it1a, lürssen, raum53, lesson4, montag, ae,
				timetableLessons);
		this.createTimeTableSession(it1a, albers, raum32, lesson5, montag, suk,
				timetableLessons);
		this.createTimeTableSession(it1a, albers, raum32, lesson6, montag, suk,
				timetableLessons);
		this.createTimeTableSession(it1a, herrmann, raum32, lesson7, montag,
				itSyst, timetableLessons);

		// Lessons for TuesDay
		this.createTimeTableSession(it1a, bastians, raum81, lesson3, dienstag,
				wug, timetableLessons);
		this.createTimeTableSession(it1a, bastians, raum81, lesson4, dienstag,
				wug, timetableLessons);
		this.createTimeTableSession(it1a, lürssen, raum53, lesson5, dienstag,
				ae, timetableLessons);
		this.createTimeTableSession(it1a, lürssen, raum53, lesson6, dienstag,
				ae, timetableLessons);

		// Lessons for Wednesday
		this.createTimeTableSession(it1a, giera, raum82, lesson3, mittwoch,
				orgaGp, timetableLessons);
		this.createTimeTableSession(it1a, giera, raum82, lesson4, mittwoch,
				orgaGp, timetableLessons);
		this.createTimeTableSession(it1a, herrmann, raum53, lesson5, mittwoch,
				itSyst, timetableLessons);
		this.createTimeTableSession(it1a, herrmann, raum53, lesson6, mittwoch,
				itSyst, timetableLessons);
		this.createTimeTableSession(it1a, burg, raum114, lesson7, mittwoch, fe,
				timetableLessons);
		this.createTimeTableSession(it1a, burg, raum114, lesson8, mittwoch, fe,
				timetableLessons);

		// Lessons for Thursday
		this.createTimeTableSession(it1a, lürssen, raum53, lesson1, donnerstag,
				ae, timetableLessons);
		this.createTimeTableSession(it1a, lürssen, raum53, lesson2, donnerstag,
				ae, timetableLessons);
		this.createTimeTableSession(it1a, giera, raum81, lesson3, donnerstag,
				orgaGp, timetableLessons);
		this.createTimeTableSession(it1a, giera, raum81, lesson4, donnerstag,
				orgaGp, timetableLessons);
		this.createTimeTableSession(it1a, wehmeyer, raum53, lesson5,
				donnerstag, pro, timetableLessons);
		this.createTimeTableSession(it1a, wehmeyer, raum53, lesson6,
				donnerstag, pro, timetableLessons);
		this.createTimeTableSession(it1a, herrmann, raum53, lesson7,
				donnerstag, pro, timetableLessons);
		this.createTimeTableSession(it1a, herrmann, raum53, lesson8,
				donnerstag, pro, timetableLessons);

		// Lessons for Friday
		this.createTimeTableSession(it1a, wehmeyer, raum53, lesson1, freitag,
				pro, timetableLessons);
		this.createTimeTableSession(it1a, wehmeyer, raum53, lesson2, freitag,
				pro, timetableLessons);
		this.createTimeTableSession(it1a, wehmeyer, raum53, lesson3, freitag,
				pro, timetableLessons);
		this.createTimeTableSession(it1a, wehmeyer, raum53, lesson4, freitag,
				pro, timetableLessons);
		this.createTimeTableSession(it1a, wehmeyer, raum53, lesson5, freitag,
				pro, timetableLessons);
		this.createTimeTableSession(it1a, wehmeyer, raum53, lesson6, freitag,
				pro, timetableLessons);

		final Timetable timetable = new Timetable();
		timetable.setLessons(timetableLessons);
		this.em.persist(timetable);

		// final EmailAddress email = new EmailAddress();
		// email.setEMailAddress("hans@wurst.de");
		// this.em.persist(email);
		//
		// final Login login1 = new Login();
		// login1.setEmail(email);
		// login1.setPassword(new PasswordEncryptor().encryptPassword("test"));
		// login1.setUser("Hans");
		// this.em.persist(login1);
		//
		// final Replacement replacement1 = new Replacement();
		// replacement1.setDate(Calendar.getInstance().getTime());
		// replacement1.setRoom(room1);
		// replacement1.setTeacher(herrmann);
		// replacement1.setForm(it1a);
		// this.em.persist(replacement1);
		//
		// timetableLesson1.setReplacement(replacement1);
		// this.em.persist(timetableLesson1);

		this.em.getTransaction().commit();

		@SuppressWarnings("unchecked")
		final List<Form> list = this.em.createNativeQuery(
				"select * from Klasse", Form.class).getResultList();
		assertTrue(list.size() >= 2);
		for (final Form current : list) {
			final String firstName = current.getDescription();
			assertTrue(firstName.equals("ita") || firstName.equals("itb"));
		}
	}

	private Teacher createTeacher(final String firstName,
			final String lastName, final String shortName) {
		final Teacher teacher = new Teacher();
		teacher.setFirstname(firstName);
		teacher.setName(lastName);
		teacher.setShortName(shortName);
		this.em.persist(teacher);
		return teacher;
	}

	private Form createForm(final String description, final Teacher teacher) {
		final Form form = new Form();
		form.setDescription("ita");
		form.setTeacher(teacher);
		this.em.persist(form);
		return form;
	}

	private Room createRoom(final String description) {
		final Room room = new Room();
		room.setDescription(description);
		this.em.persist(room);
		return room;
	}

	private Lesson createLesson(final String timeFrom, final String timeTo) {
		final Lesson lesson = new Lesson();
		lesson.setTimeFrom(Time.valueOf(timeFrom));
		lesson.setTimeTo(Time.valueOf(timeTo));
		this.em.persist(lesson);
		return lesson;
	}

	private Subject createSubject(final String description,
			final String shortName) {
		final Subject subject = new Subject();
		subject.setDescription(description);
		subject.setShortName(shortName);
		this.em.persist(subject);
		return subject;
	}

	private Day createDay(final String description) {
		final Day day = new Day();
		day.setDescription(description);
		this.em.persist(day);
		return day;
	}

	private TimetableLesson createTimeTableSession(final Form form,
			final Teacher teacher, final Room room, final Lesson lesson,
			final Day day, final Subject subject,
			final List<TimetableLesson> timetableLessons) {

		final TimetableLesson timetableLesson = new TimetableLesson();
		timetableLesson.setForm(form);
		timetableLesson.setTeacher(teacher);
		timetableLesson.setRoom(room);
		timetableLesson.setLesson(lesson);
		timetableLesson.setDay(day);
		timetableLesson.setSubject(subject);
		timetableLessons.add(timetableLesson);
		return timetableLesson;
	}

}