package server.persistance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.persistence.HibernateUtil;
import storage.entities.Klasse;
import storage.entities.Lehrer;

public class HibernateTest {

	private EntityManager em;

	@Before
	public void init() {
		this.em = new HibernateUtil().getEntityManager();
	}

	@After
	public void cleanup() {
		this.em.close();
	}

	@Test
	public void entityManagerTest() {
		this.em.getTransaction().begin();
		final Klasse klasse1 = new Klasse();
		klasse1.setBezeichung("ita");
		final Klasse klasse2 = new Klasse();
		klasse2.setBezeichung("itb");
		this.em.persist(klasse1);
		this.em.persist(klasse2);
		this.em.getTransaction().commit();

		final List<Klasse> list = this.em.createQuery("select p from Klasse p")
				.getResultList();
		assertEquals(2, list.size());
		for (final Klasse current : list) {
			final String firstName = current.getBezeichung();
			assertTrue(firstName.equals("ita") || firstName.equals("itb"));
		}
	}

	@Test
	public void stundenplanStundeTest() {
		this.em.getTransaction().begin();

		final Klasse klasse1 = new Klasse();
		klasse1.setBezeichung("ita");

		final Lehrer lehrer1 = new Lehrer();
		lehrer1.setKurzName("Hr");
		lehrer1.setName("Hermann");
		lehrer1.setVorname("Werner");

		// Raum raum = new Raum();
		// raum.setBezeichnung("53");
		//
		// Unterrichtsfach fach = new Unterrichtsfach();
		// fach.setBezeichnung("Wirtschaft und Gesellschaft");
		// fach.setKurzName("WuG");
		//
		// Tag tag = new Tag();
		// tag.setBezeichnung("Montag");
		//
		// Stunde stunde = new Stunde();
		// stunde.setZeitBis(new Date());
		// stunde.setZeitVon(new Date());
		//
		// Vertretung vertretung = new Vertretung();
		// vertretung.setDatum(new Date());

		// StundenplanStunde plan = new StundenplanStunde();
		// plan.setKlasse(klasse1);
		// plan.setLehrer(lehrer1);
		// em.persist(plan);

		this.em.persist(klasse1);
		this.em.persist(lehrer1);

		this.em.getTransaction().commit();

		final List<Klasse> list = this.em.createQuery("select p from Klasse p")
				.getResultList();
		assertEquals(1, list.size());
	}
}