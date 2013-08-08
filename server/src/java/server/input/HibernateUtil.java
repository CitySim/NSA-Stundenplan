package server.input;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

	private static final EntityManagerFactory emf;

	private static final EntityManager entityManager;

	static {
		emf = Persistence.createEntityManagerFactory("nsa-stundenplan");
		entityManager = emf.createEntityManager();
	}

	public static EntityManager getEntityManager() {
		return entityManager;
	}
}
