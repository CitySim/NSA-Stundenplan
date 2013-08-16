package server.queries;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.itextpdf.text.log.SysoCounter;

import server.entities.EmailAdress;
import server.entities.Login;
import server.persistence.HibernateUtil;

public class LoginQuery {
	private final EntityManager em;

	public LoginQuery() {
		this.em = HibernateUtil.getEntityManager();
	}

	/**
	 *  Get the Password for the specified User
	 * @param username
	 * @return
	 */
	public String getPassword(final String username) {	
		return getLoginUser(username).getPassword();
	}

	/**
	 * Create a new User with the specified Details
	 * @param username
	 * @param password
	 * @param eMailAddress
	 */
	public void createUser(final String username, final String password,
			final String eMailAddress) {
			
		this.em.getTransaction().begin();

		EmailAdress email = new EmailAdress();
		email.setEmailAddress(eMailAddress);
		this.em.persist(email);
		
		Login login = new Login();
		login.setPassword(password);
		login.setUser(username);
		login.setEmail(email);
		this.em.persist(login);
		this.em.getTransaction().commit();
	}

	/**
	 * Changes the User password
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
	
	private Login getLoginUser (String username){
		Login login = null;
		final List<Login> loginList = this.em.createNativeQuery(
				"select * from Login WHERE user ='"+username+"'", Login.class).getResultList();
		if(loginList.size() == 1){
			login = loginList.get(0);
		}
		return login;
	}
	
}
