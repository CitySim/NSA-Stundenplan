package server.queries;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import server.entities.EmailAddress;
import server.entities.Login;
import server.persistence.HibernateUtil;

/**
 * Creates/Deletes/Checks the Login and inputs them into the Database
 * @author oleg.scheltow
 *
 */
public class LoginQuery extends QueryResult{

	public LoginQuery() {
		super();
	}

	/**
	 *  Get the Password for the specified User
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
	 * @param username
	 * @param password
	 * @param eMailAddress
	 */
	public boolean createUser(final String username, final String password,
			final String eMailAddress) {
		Login login = getLoginUser(username);	
		if(login == null){
			em.getTransaction().begin();

			EmailAddress email = new EmailAddress();
			email.setEMailAddress(eMailAddress);
			em.persist(email);
		
			login = new Login();
			login.setPassword(password);
			login.setUser(username);
			login.setEmail(email);
			em.persist(login);
			em.getTransaction().commit();
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Removes the User Login
	 * @param username
	 * @return
	 */
	public boolean removeLogin(String username){
		Login login = getLoginUser(username);
		return removeFromDB(login);

	}

	/**
	 * Changes the User password
	 * @param username
	 * @param password
	 */
	public boolean changePassword(final String username, final String password) {
		em.getTransaction().begin();
		Login loginUser = getLoginUser(username);
		if(loginUser != null){
			loginUser.setPassword(password);
			em.persist(loginUser);
			em.getTransaction().commit();
			return true;
		}else{
			return false;
		}
	}
	
	private Login getLoginUser(String username) {	
		return (Login) getSingleResult(em.createQuery(
				"select l from Login l where user = '" + username + "'",
				Login.class));
	}
	
}
