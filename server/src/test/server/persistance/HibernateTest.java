package server.persistance;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.persistance.HibernateUtil;
import storage.entities.Klasse;
import storage.entities.Lehrer;
import storage.entities.Raum;
import storage.entities.Stunde;
import storage.entities.StundenplanStunde;
import storage.entities.Tag;
import storage.entities.Unterrichtsfach;
import storage.entities.Vertretung;

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

//	@Test
//	public void entityManagerTest() {
//		Klasse klasse1 = new Klasse();
//		klasse1.setBezeichung("ita");
//		Klasse klasse2 = new Klasse();
//		klasse2.setBezeichung("itb");
//		em.persist(klasse1);
//		em.persist(klasse2);
//		em.getTransaction().commit();
//		
//		final List<Klasse> list = em.createQuery("select p from Klasse p").getResultList();
//		assertEquals(2, list.size());
//		for (Klasse current : list) {
//			final String firstName = current.getBezeichung();
//			assertTrue(firstName.equals("ita") || firstName.equals("itb"));
//		}
//	}
//	
	@Test
	public void stundenplanStundeTest(){
		em.getTransaction().begin();

		Klasse klasse1 = new Klasse();
		klasse1.setBezeichung("ita");
		
		Lehrer lehrer1 = new Lehrer();
		lehrer1.setKurzName("Hr");
		lehrer1.setName("Hermann");
		lehrer1.setVorname("Werner");
		
//		Raum raum = new Raum();
//		raum.setBezeichnung("53");
//		
//		Unterrichtsfach fach = new Unterrichtsfach();
//		fach.setBezeichnung("Wirtschaft und Gesellschaft");
//		fach.setKurzName("WuG");
//		
//		Tag tag = new Tag();
//		tag.setBezeichnung("Montag");
//		
//		Stunde stunde = new Stunde();
//		stunde.setZeitBis(new Date());
//		stunde.setZeitVon(new Date());
//		
//		Vertretung vertretung = new Vertretung();
//		vertretung.setDatum(new Date());

//		StundenplanStunde plan = new StundenplanStunde();
//		plan.setKlasse(klasse1);
//		plan.setLehrer(lehrer1);
//		em.persist(plan);

		em.persist(klasse1);
		em.persist(lehrer1);

		em.getTransaction().commit();
		
		final List<Klasse> list = em.createQuery("select p from Klasse p").getResultList();
		assertEquals(1, list.size());
	}
}