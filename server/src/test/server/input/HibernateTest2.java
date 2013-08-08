package server.input;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import storage.entities.Klasse;

public class HibernateTest2 {
	
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private Session session;

	@Before
	public void init() {

		entityManagerFactory = Persistence.createEntityManagerFactory("nsa-stundenplan");
		entityManager = entityManagerFactory.createEntityManager();
		session = HibernateUtil.getSessionFactory().openSession();
	}

	@After
	public void cleanup() {
		entityManager.close();
	}

	@Test
	public void emptyTest() {
		entityManager.getTransaction().begin();
		Klasse klasse1 = new Klasse();
		klasse1.setBezeichung("ita");
		Klasse klasse2 = new Klasse();
		klasse2.setBezeichung("itb");
		entityManager.persist(klasse1);
		entityManager.persist(klasse2);
		entityManager.getTransaction().commit();
		
		final List<Klasse> list = entityManager.createQuery("select p from Klasse p").getResultList();
		assertEquals(2, list.size());
		for (Klasse current : list) {
			final String firstName = current.getBezeichung();
			assertTrue(firstName.equals("ita") || firstName.equals("itb"));
		}
	}
}