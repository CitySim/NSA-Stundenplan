package server.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

	private static EntityManagerFactory emf = null;

	private static EntityManager entityManager = null;

	static {
		if (emf == null) {

			final boolean exists = HibernateUtil.checkDatabase();
			if (exists) {
				emf = Persistence.createEntityManagerFactory("nsa-stundenplan-update");
			} else {
				emf = Persistence.createEntityManagerFactory("nsa-stundenplan-create");
			}
		}

		if (entityManager == null) {
			entityManager = emf.createEntityManager();
		}
	}

	public static EntityManager getEntityManager() {
		return entityManager;
	}

	private static boolean checkDatabase() {
		// final int port = 3306;
		// final String userName = "root";
		// final String password = "";
		// Connection con = null;
		// Statement stmt = null;
		// try {
		// final String url = "jdbc:mysql://localhost:" + port + "/test";
		// con = DriverManager.getConnection(url, userName, password);
		// } catch (final Exception e) {
		// throw new DataBaseConnectionException();
		// return false;
		// }
		// try {
		// stmt = con.createStatement();
		// } catch (final SQLException e) {
		// e.printStackTrace();
		// }
		// try {
		// final ResultSet rs =
		// stmt.executeQuery("SELECT * FROM emailaddress WHERE id ='1'");
		// if (rs != null) {
		// return true;
		// } else {
		// return false;
		// }
		// } catch (final SQLException e) {
		// return false;
		// }
		return true;
	}
}
