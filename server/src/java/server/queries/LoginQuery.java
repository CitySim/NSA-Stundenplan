package server.queries;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import server.entities.Login;
import server.persistence.HibernateUtil;

public class LoginQuery {
	String password = null;
	private final EntityManager em;

	public LoginQuery() {
		this.em = HibernateUtil.getEntityManager();
	}

	public String getPassword(final String username) {

		final List<Login> loginList = this.em.createNativeQuery(
				"select * from Login", Login.class).getResultList();
		for (final Login login : loginList) {
			if (username.equals(login.getUser())) {
				this.password = login.getPassword();
			}
		}
		return this.password;
	}

	public void createUser(final String username, final String password,
			final String eMailAddress) {
		this.em.getTransaction().begin();
		final Query persistableQuery = HibernateUtil.getEntityManager()
				.createNativeQuery(
						"INSERT INTO Login(`password`, `user`) VALUES ('"
								+ password + "','" + username + "')",
						Login.class);
		persistableQuery.executeUpdate();
		this.em.getTransaction().commit();
	}

	public void changePassword(final String username, final String password) {
		this.em.getTransaction().begin();
		final Query persistableQuery = HibernateUtil.getEntityManager()
				.createNativeQuery(
						"UPDATE Login set password='" + password
								+ "' WHERE user = '" + username + "'",
						Login.class);
		persistableQuery.executeUpdate();
		this.em.getTransaction().commit();
	}
}
