package server.persistence;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
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

/**
 * Test for the initial dataCreation.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class DataCreationTest {

	private EntityManager em;
	private DataCreationHelper helper;

	@Before
	public void init() {
		this.em = HibernateUtil.getEntityManager();
		this.helper = new DataCreationHelper(this.em);
	}

	@After
	public void cleanup() {
		this.em.close();
	}

	@SuppressWarnings("unused")
	@Test
	public void testDataCreation() {

		this.em.getTransaction().begin();

		final Teacher lührssen = this.helper.createTeacher("Volker", "Lührssen", "Lü");
		final Teacher albers = this.helper.createTeacher("Kirsten", "Albers", "Al");
		final Teacher herrmann = this.helper.createTeacher("Werner", "Herrmann", "Hr");
		final Teacher bastians = this.helper.createTeacher("Thomas", "Bastians", "Ba");
		final Teacher giera = this.helper.createTeacher("Heike", "Giera", "Ga");
		final Teacher wehmeyer = this.helper.createTeacher("Markus", "Wehmeyer", "Wm");
		final Teacher burg = this.helper.createTeacher("Burg", "Burg", "Bu");

		final Form it1a = this.helper.createForm("it1a", herrmann);
		final Form it1b = this.helper.createForm("it1b", lührssen);

		final Room raum32 = this.helper.createRoom("Raum 32");
		final Room raum53 = this.helper.createRoom("Raum 53");
		final Room raum81 = this.helper.createRoom("Raum 81");
		final Room raum82 = this.helper.createRoom("Raum 82");
		final Room raum114 = this.helper.createRoom("Raum 144");

		final Lesson lesson1 = this.helper.createLesson("07:45:00", "08:30:00");
		final Lesson lesson2 = this.helper.createLesson("08:30:00", "09:15:00");
		final Lesson lesson3 = this.helper.createLesson("09:30:00", "10:15:00");
		final Lesson lesson4 = this.helper.createLesson("10:15:00", "11:00:00");
		final Lesson lesson5 = this.helper.createLesson("11:30:00", "12:15:00");
		final Lesson lesson6 = this.helper.createLesson("12:15:00", "13:00:00");
		final Lesson lesson7 = this.helper.createLesson("13:15:00", "14:00:00");
		final Lesson lesson8 = this.helper.createLesson("14:00:00", "14:45:00");
		final Lesson lesson9 = this.helper.createLesson("15:00:00", "15:45:00");
		final Lesson lesson10 = this.helper.createLesson("15:45:00", "16:30:00");

		final Subject ae = this.helper.createSubject("Anwendungsentwicklung", "AE");
		final Subject suk = this.helper.createSubject("Sprache und Kommunikation", "SuK");
		final Subject itSyst = this.helper.createSubject("IT-Systeme", "IT-Syst");
		final Subject wug = this.helper.createSubject("Wirtschaft und Gesellschaft", "WuG");
		final Subject orgaGp = this.helper.createSubject("Organisation und Geschäftsprozesse", "OrgaGp");
		final Subject fe = this.helper.createSubject("Fachenglisch", "FE");
		final Subject pro = this.helper.createSubject("Projekt", "Pro");

		final Day montag = this.helper.createDay("Montag");
		final Day dienstag = this.helper.createDay("Diestag");
		final Day mittwoch = this.helper.createDay("Mittwoch");
		final Day donnerstag = this.helper.createDay("Donnerstag");
		final Day freitag = this.helper.createDay("Freitag");

		final List<TimetableLesson> timetableLessons = new ArrayList<TimetableLesson>();

		// Lessons for Monday
		final TimetableLesson timetableLesson = this.helper.createTimeTableSession(it1a, lührssen, raum53, lesson3, montag, ae, timetableLessons);
		this.helper.createTimeTableSession(it1a, lührssen, raum53, lesson4, montag, ae, timetableLessons);
		this.helper.createTimeTableSession(it1a, albers, raum32, lesson5, montag, suk, timetableLessons);
		this.helper.createTimeTableSession(it1a, albers, raum32, lesson6, montag, suk, timetableLessons);
		this.helper.createTimeTableSession(it1a, herrmann, raum32, lesson7, montag, itSyst, timetableLessons);

		// Lessons for TuesDay
		this.helper.createTimeTableSession(it1a, bastians, raum81, lesson3, dienstag, wug, timetableLessons);
		this.helper.createTimeTableSession(it1a, bastians, raum81, lesson4, dienstag, wug, timetableLessons);
		this.helper.createTimeTableSession(it1a, lührssen, raum53, lesson5, dienstag, ae, timetableLessons);
		this.helper.createTimeTableSession(it1a, lührssen, raum53, lesson6, dienstag, ae, timetableLessons);

		// Lessons for Wednesday
		this.helper.createTimeTableSession(it1a, giera, raum82, lesson3, mittwoch, orgaGp, timetableLessons);
		this.helper.createTimeTableSession(it1a, giera, raum82, lesson4, mittwoch, orgaGp, timetableLessons);
		this.helper.createTimeTableSession(it1a, herrmann, raum53, lesson5, mittwoch, itSyst, timetableLessons);
		this.helper.createTimeTableSession(it1a, herrmann, raum53, lesson6, mittwoch, itSyst, timetableLessons);
		this.helper.createTimeTableSession(it1a, burg, raum114, lesson7, mittwoch, fe, timetableLessons);
		this.helper.createTimeTableSession(it1a, burg, raum114, lesson8, mittwoch, fe, timetableLessons);

		// Lessons for Thursday
		this.helper.createTimeTableSession(it1a, lührssen, raum53, lesson1, donnerstag, ae, timetableLessons);
		this.helper.createTimeTableSession(it1a, lührssen, raum53, lesson2, donnerstag, ae, timetableLessons);
		this.helper.createTimeTableSession(it1a, giera, raum81, lesson3, donnerstag, orgaGp, timetableLessons);
		this.helper.createTimeTableSession(it1a, giera, raum81, lesson4, donnerstag, orgaGp, timetableLessons);
		this.helper.createTimeTableSession(it1a, wehmeyer, raum53, lesson5, donnerstag, pro, timetableLessons);
		this.helper.createTimeTableSession(it1a, wehmeyer, raum53, lesson6, donnerstag, pro, timetableLessons);
		this.helper.createTimeTableSession(it1a, herrmann, raum53, lesson7, donnerstag, pro, timetableLessons);
		this.helper.createTimeTableSession(it1a, herrmann, raum53, lesson8, donnerstag, pro, timetableLessons);

		// Lessons for Friday
		this.helper.createTimeTableSession(it1a, wehmeyer, raum53, lesson1, freitag, pro, timetableLessons);
		this.helper.createTimeTableSession(it1a, wehmeyer, raum53, lesson2, freitag, pro, timetableLessons);
		this.helper.createTimeTableSession(it1a, wehmeyer, raum53, lesson3, freitag, pro, timetableLessons);
		this.helper.createTimeTableSession(it1a, wehmeyer, raum53, lesson4, freitag, pro, timetableLessons);
		this.helper.createTimeTableSession(it1a, wehmeyer, raum53, lesson5, freitag, pro, timetableLessons);
		this.helper.createTimeTableSession(it1a, wehmeyer, raum53, lesson6, freitag, pro, timetableLessons);

		final Timetable timetable = new Timetable();
		timetable.setLessons(timetableLessons);
		this.em.persist(timetable);

		this.helper.createLogin("Volker.Lürssen", "Volker.Lürssen@g18.de");
		this.helper.createLogin("Kirsten.Albers", "Kirsten.Albers@g18.de");
		this.helper.createLogin("Werner.Herrmann", "Werner.Herrmann@g18.de");
		this.helper.createLogin("Thomas.Bastians", "Thomas.Bastians@g18.de");
		this.helper.createLogin("Heike.Giera", "Heike.Giera@g18.de");
		this.helper.createLogin("Markus.Wehmeyer", "Markus.Wehmeyer@g18.de");
		this.helper.createLogin("Burg.Burg", "Burg.Burg@g18.de");
		this.helper.createLogin("test", "test@g18.de");

		this.helper.createReplacement(Calendar.getInstance().getTime(), raum53, lührssen, it1a, timetableLesson);

		this.em.getTransaction().commit();

		@SuppressWarnings("unchecked")
		final List<Form> list = this.em.createNativeQuery("select * from Klasse", Form.class).getResultList();
		assertTrue(list.size() >= 2);
	}

}