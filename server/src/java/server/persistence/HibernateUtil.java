package server.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

	private static EntityManagerFactory emf = null;

	private static EntityManager entityManager = null;

	static {
		if (emf == null) {
			HibernateUtil.checkDatabase();
			final boolean exists = true;
			if (exists) {
				emf = Persistence.createEntityManagerFactory("nsa-stundenplan");
			} else {
				emf = Persistence.createEntityManagerFactory("nsa-stundenplan-new");
			}
		}

		if (entityManager == null) {
			entityManager = emf.createEntityManager();
		}
	}

	public static EntityManager getEntityManager() {
		return entityManager;
	}

	public static void checkDatabase() {
		// TODO CHECK IF DB final EXISTS
		final Connection con = null;
		Statement stmt = null;
		try {
			stmt = con.createStatement();
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		try {
			final ResultSet rs = stmt.executeQuery("SELECT * FROM test");
			System.out.println(rs.last());
		} catch (final SQLException e) {
			e.printStackTrace();
		}
	}
}
