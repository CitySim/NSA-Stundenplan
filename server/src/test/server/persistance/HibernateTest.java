package server.persistance;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.persistance.HibernateUtil;
import storage.entities.Klasse;

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
		Klasse klasse1 = new Klasse();
		klasse1.setBezeichung("ita");
		Klasse klasse2 = new Klasse();
		klasse2.setBezeichung("itb");
		em.persist(klasse1);
		em.persist(klasse2);
		em.getTransaction().commit();
		
		final List<Klasse> list = em.createQuery("select p from Klasse p").getResultList();
		assertEquals(2, list.size());
		for (Klasse current : list) {
			final String firstName = current.getBezeichung();
			assertTrue(firstName.equals("ita") || firstName.equals("itb"));
		}
	}
}