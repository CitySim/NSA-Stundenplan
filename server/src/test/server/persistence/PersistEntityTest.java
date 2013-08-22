package server.persistence;

import static org.junit.Assert.assertTrue;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.entities.Day;
import server.entities.EmailAddress;
import server.entities.Form;
import server.entities.Lesson;
import server.entities.Login;
import server.entities.Replacement;
import server.entities.Room;
import server.entities.Subject;
import server.entities.Teacher;
import server.entities.Timetable;
import server.entities.TimetableLesson;
import server.operations.PasswordEncryptor;

public class PersistEntityTest {

	private EntityManager em;

	@Before
	public void init() {
		em = HibernateUtil.getEntityManager();
	}

	@After
	public void cleanup() {
		em.close();
	}

	@Test
	public void entityManagerTest() {

		em.getTransaction().begin();

		final Teacher teacher1 = new Teacher();
		teacher1.setFirstname("Hans");
		teacher1.setName("Wurst");
		teacher1.setShortName("WUHA");
		em.persist(teacher1);

		final Form form1 = new Form();
		form1.setDescription("ita");
		form1.setTeacher(teacher1);
		em.persist(form1);

		final Teacher teacher2 = new Teacher();
		teacher2.setFirstname("Gesinde");
		teacher2.setName("Hammel");
		teacher2.setShortName("HaGe");
		em.persist(teacher2);

		final Form form2 = new Form();
		form2.setTeacher(teacher2);
		form2.setDescription("itb");
		em.persist(form2);

		final Room room1 = new Room();
		room1.setDescription("raum53");
		em.persist(room1);

		final Room room2 = new Room();
		room2.setDescription("raum53");
		em.persist(room2);

		final Lesson lesson1 = new Lesson();
		lesson1.setTimeFrom(Time.valueOf("10:00:00"));
		lesson1.setTimeTo(Time.valueOf("10:45:00"));
		em.persist(lesson1);

		final Lesson lesson2 = new Lesson();
		lesson2.setTimeFrom(Time.valueOf("10:45:00"));
		lesson2.setTimeTo(Time.valueOf("11:30:00"));
		em.persist(lesson2);

		final Subject subject1 = new Subject();
		subject1.setDescription("Biologie");
		subject1.setShortName("Bio");
		em.persist(subject1);

		final Subject subject2 = new Subject();
		subject2.setDescription("System Gammelierung");
		subject2.setShortName("SYSGAM");
		em.persist(subject2);

		final Day day1 = new Day();
		day1.setDescription("Montag");
		em.persist(day1);

		final Day day2 = new Day();
		day2.setDescription("Dienstag");
		em.persist(day2);

		final TimetableLesson timetableLesson1 = new TimetableLesson();
		timetableLesson1.setForm(form1);
		timetableLesson1.setTeacher(teacher1);
		timetableLesson1.setRoom(room1);
		timetableLesson1.setLesson(lesson1);
		timetableLesson1.setDay(day1);
		timetableLesson1.setSubject(subject1);

		final TimetableLesson timetableLesson2 = new TimetableLesson();
		timetableLesson2.setForm(form1);
		timetableLesson2.setTeacher(teacher2);
		timetableLesson2.setRoom(room2);
		timetableLesson2.setLesson(lesson2);
		timetableLesson2.setDay(day1);
		timetableLesson2.setSubject(subject2);
		em.persist(timetableLesson2);

		final List<TimetableLesson> timetableLessons = new ArrayList<TimetableLesson>();
		timetableLessons.add(timetableLesson1);
		timetableLessons.add(timetableLesson2);

		final Timetable timetable1 = new Timetable();
		timetable1.setLessons(timetableLessons);
		em.persist(timetable1);

		final EmailAddress email = new EmailAddress();
		email.setEMailAddress("hans@wurst.de");
		em.persist(email);

		final Login login1 = new Login();
		login1.setEmail(email);
		login1.setPassword(new PasswordEncryptor().encryptPassword("test"));
		login1.setUser("Hans");
		em.persist(login1);

		final Replacement replacement1 = new Replacement();
		replacement1.setDate(Calendar.getInstance().getTime());
		replacement1.setRoom(room1);
		replacement1.setTeacher(teacher1);
		replacement1.setForm(form1);
		em.persist(replacement1);

		timetableLesson1.setReplacement(replacement1);
		em.persist(timetableLesson1);

		em.getTransaction().commit();

		@SuppressWarnings("unchecked")
		final List<Form> list = em.createNativeQuery(
				"select * from Klasse", Form.class).getResultList();
		assertTrue(list.size() >= 2);
		for (final Form current : list) {
			final String firstName = current.getDescription();
			assertTrue(firstName.equals("ita") || firstName.equals("itb"));
		}
	}
}