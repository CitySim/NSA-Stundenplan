package server.input;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import storage.entities.Klasse;

public class HibernateTest {
	private EntityManagerFactory emf;

	private EntityManager em;

	@Before
	public void init() {

		emf = Persistence.createEntityManagerFactory("nsa-stundenplan");
		em = emf.createEntityManager();
	}

	@After
	public void cleanup() {
		em.close();
	}

	@Test
	public void emptyTest() {
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