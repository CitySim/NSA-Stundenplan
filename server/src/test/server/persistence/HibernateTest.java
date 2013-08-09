package server.persistence;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.persistence.HibernateUtil;
import server.entities.Form;

public class HibernateTest {

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
		Form class1 = new Form();
		class1.setDescription("ita");
		Form class2 = new Form();
		class2.setDescription("itb");
		
		em.persist(class1);
		em.persist(class2);
		em.getTransaction().commit();
		
		final List<Form> list = em.createNativeQuery("select * from Klasse", Form.class).getResultList();
		assertEquals(2, list.size());
		for (Form current : list) {
			final String firstName = current.getDescription();
			assertTrue(firstName.equals("ita") || firstName.equals("itb"));
		}
	}
}