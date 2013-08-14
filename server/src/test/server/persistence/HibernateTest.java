package server.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.entities.Form;

public class HibernateTest {

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
		final Form class1 = new Form();
		class1.setDescription("ita");
		final Form class2 = new Form();
		class2.setDescription("itb");

		this.em.persist(class1);
		this.em.persist(class2);
		this.em.getTransaction().commit();

		final List<Form> list = this.em.createNativeQuery(
				"select * from Klasse", Form.class).getResultList();
		assertEquals(2, list.size());
		for (final Form current : list) {
			final String firstName = current.getDescription();
			assertTrue(firstName.equals("ita") || firstName.equals("itb"));
		}
	}
}