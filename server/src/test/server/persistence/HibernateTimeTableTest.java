package server.persistence;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.entities.TimetableLesson;

public class HibernateTimeTableTest {

	private EntityManager em;

	@Before
	public void init() {
		this.em = HibernateUtil.getEntityManager();
	}

	@After
	public void cleanup() {
		this.em.close();
	}

	// Only Testable if Database is filled with testdata from SQL Querys
	@Test
	public void sizeTest() {

		@SuppressWarnings("unchecked")
		final List<TimetableLesson> list = this.em.createNativeQuery(
				"select * from Klasse_Tag_Stunde", TimetableLesson.class)
				.getResultList();

		assertEquals(3, list.size());
		assertEquals("Werner", list.get(0).getTeacher().getFirstname());
	}

}