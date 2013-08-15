package server.persistence;

import static org.junit.Assert.assertEquals;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.entities.Form;
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

	@Test
	public void entityManagerTest() {

		final List<Form> list = this.em.createNativeQuery(
				"select * from Klasse_Tag_Stunde", TimetableLesson.class).getResultList();
		
		assertEquals(0, list.size());
	}
}