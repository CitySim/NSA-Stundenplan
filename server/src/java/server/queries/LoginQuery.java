package server.queries;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import server.entities.Login;
import server.persistence.HibernateUtil;

public class LoginQuery {
	String password = null;
	private EntityManager em;

	public LoginQuery(){
		this.em = HibernateUtil.getEntityManager();
	}

	public String getPassword(String username){
		
		List<Login> loginList =  HibernateUtil.getEntityManager()
					.createNativeQuery("select * from Login", Login.class)
					.getResultList();
		for (Login login : loginList) {
			if(username.equals(login.getUser())){
				password = login.getPassword();
			}
		}
		return password;
	}
	
	public void createUser(String username,String password){
		this.em.getTransaction().begin();
		Query  persistableQuery = HibernateUtil.getEntityManager().createNativeQuery("INSERT INTO `login`(`password`, `user`) VALUES ("+password+","+username+")", Login.class);
		persistableQuery.executeUpdate();
		this.em.close();

	}
	
	public void changePassword(String username,String password){
		this.em.getTransaction().begin();
		Query  persistableQuery = HibernateUtil.getEntityManager().createNativeQuery("UPDATE `login` SET `password`="+password+" WHERE user = "+username+")", Login.class);
		persistableQuery.executeUpdate();
		this.em.close();

	}
}
