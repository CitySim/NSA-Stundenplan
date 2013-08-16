package server.queries;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import server.entities.EmailAddress;
import server.entities.Login;
import server.persistence.HibernateUtil;

/**
 * Creates/Deletes/Checks the Login and inputs them into the Database
 * 
 * @author oleg.scheltow
 * 
 */
public class LoginQuery {
	private final EntityManager em;

	public LoginQuery() {
		this.em = HibernateUtil.getEntityManager();
	}

	/**
	 * Get the Password for the specified User
	 * 
	 * @param username
	 * @return
	 */
	public String getPassword(final String username) {
		Login login = getLoginUser(username);
		if (login != null) {
			return login.getPassword();
		}
		return null;
	}

	/**
	 * Create a new User with the specified Details
	 * 
	 * @param username
	 * @param password
	 * @param eMailAddress
	 */
	public boolean createUser(final String username, final String password,
			final String eMailAddress) {
		Login login = getLoginUser(username);
		if (login == null) {
			this.em.getTransaction().begin();

			EmailAddress email = new EmailAddress();
			email.setEMailAddress(eMailAddress);
			this.em.persist(email);

			login = new Login();
			login.setPassword(password);
			login.setUser(username);
			login.setEmail(email);
			this.em.persist(login);
			this.em.getTransaction().commit();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Removes the User Login
	 * 
	 * @param username
	 * @return
	 */
	public boolean removeLogin(String username) {
		Login login = getLoginUser(username);
		if (login == null) {
			return false;
		} else {
			this.em.getTransaction().begin();
			this.em.remove(login);
			this.em.getTransaction().commit();
			return true;
		}
	}

	/**
	 * Changes the User password
	 * 
	 * @param username
	 * @param password
	 */
	public void changePassword(final String username, final String password) {
		this.em.getTransaction().begin();
		Login loginUser = getLoginUser(username);
		loginUser.setPassword(password);
		this.em.persist(loginUser);
		this.em.getTransaction().commit();
	}

	private Login getLoginUser(String username) {
		Login login = null;
		try {login = em.createQuery(
				"select l from Login l where user = '" + username + "'",
				Login.class).getSingleResult();}
		catch (NoResultException e){
			
		}
		return login;
	}

}
