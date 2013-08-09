package server.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

	private static EntityManagerFactory emf = null;

	private static EntityManager entityManager = null;

	static {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("nsa-stundenplan");
		}
		
		if (entityManager == null){
			entityManager = emf.createEntityManager();
		}
	}

	public static EntityManager getEntityManager() {
		return entityManager;
	}
}
