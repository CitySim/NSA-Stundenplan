package server.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Time;
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

public class PersistEntityTest {

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
		
		Teacher teacher1 = new Teacher();
		teacher1.setFirstname("Hans");
		teacher1.setName("Wurst");
		teacher1.setShortName("WUHA");
		em.persist(teacher1);
		
		final Form class1 = new Form();
		class1.setDescription("ita");
		class1.setTeacher(teacher1);
		this.em.persist(class1);
		
		Teacher teacher2 = new Teacher();
		teacher2.setFirstname("Gesinde");
		teacher2.setName("Hammel");
		teacher2.setShortName("HaGe");
		em.persist(teacher2);
		
		final Form class2 = new Form();
		class2.setTeacher(teacher2);
		class2.setDescription("itb");
		this.em.persist(class2);
		
		Room room = new Room();
		room.setDescription("raum53");
		em.persist(room);
		
		Lesson lesson = new Lesson();
		lesson.setTimeFrom(Time.valueOf("10:00:00"));
		lesson.setTimeTo(Time.valueOf("10:00:00"));
		em.persist(lesson);
		
		Subject subject = new Subject();
		subject.setDescription("Biologie");
		subject.setShortName("Bio");
		em.persist(subject);
		
		Day day = new Day();
		day.setDescription("Montag");
		em.persist(day);
		
		this.em.getTransaction().commit();

		@SuppressWarnings("unchecked")
		final List<Form> list = this.em.createNativeQuery(
				"select * from Klasse", Form.class).getResultList();
		assertEquals(2, list.size());
		for (final Form current : list) {
			final String firstName = current.getDescription();
			assertTrue(firstName.equals("ita") || firstName.equals("itb"));
		}
	}
}