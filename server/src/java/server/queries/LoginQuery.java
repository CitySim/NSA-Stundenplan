package server.queries;

import server.entities.EmailAddress;
import server.entities.Login;

/**
 * Creates/Deletes/Checks the Login and inputs them into the Database.
 * 
 * @author oleg.scheltow
 * 
 */
public class LoginQuery extends QueryResult {

	public LoginQuery() {
		super();
	}

	/**
	 * Get the Password for the specified User.
	 * 
	 * @param username
	 * @return String password
	 */
	public String getPassword(final String username) {
		final Login login = this.getLogin(username);
		if (login != null) {
			return login.getPassword();
		}
		return null;
	}

	/**
	 * Gets the Email address for the specified User.
	 * 
	 * @param username
	 * @return String Email
	 */
	public String getEmailForUser(final String username) {
		final Login login = this.getLogin(username);
		if (login != null) {
			return login.getEmail().getEMailAddress();
		}
		return null;
	}

	/**
	 * Create a new User with the specified Details.
	 * 
	 * @param username
	 * @param password
	 * @param eMailAddress
	 * @return boolean successful
	 */
	public Login createUser(final String username, final String password, final String eMailAddress) {
		Login login = this.getLogin(username);
		if (login == null) {
			this.em.getTransaction().begin();

			EmailAddress email = this.getEmail(eMailAddress);
			if (email == null) {
				email = new EmailAddress();
				email.setEMailAddress(eMailAddress);
				this.em.persist(email);
			}

			login = new Login();
			login.setPassword(password);
			login.setUser(username);
			login.setEmail(email);
			this.em.persist(login);
			this.em.getTransaction().commit();
		} 
		return login;
	}

	/**
	 * Removes the User Login.
	 * 
	 * @param username
	 * @return boolean successful
	 */
	public boolean removeLogin(final String username) {
		final Login login = this.getLogin(username);
		return this.removeFromDB(login);

	}

	/**
	 * Changes the login password.
	 * 
	 * @param login
	 * @param password
	 * @return boolean success
	 */
	public boolean changePassword(final Login login, final String password) {
		if (login != null) {
			this.em.getTransaction().begin();
			login.setPassword(password);
			this.em.persist(login);
			this.em.getTransaction().commit();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Gets existing Email address.
	 * 
	 * @param formString
	 * @return EmailAddress
	 */
	private EmailAddress getEmail(final String mail) {
		return (EmailAddress) this.getSingleResult(this.em.createNativeQuery("select * from emailaddress WHERE eMailAddress ='" + mail + "'",
				EmailAddress.class));
	}

	public Login getLogin(final String username) {
		return (Login) this.getSingleResult(this.em.createQuery("select l from Login l where user = '" + username + "'", Login.class));
	}
	public Login getLoginById(final int userId) {
		return (Login) this.getSingleResult(this.em.createQuery("from Login l where id = '" + userId + "'", Login.class));
	}
}