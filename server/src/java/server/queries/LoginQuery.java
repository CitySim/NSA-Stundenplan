package server.queries;

import java.util.List;

import server.entities.Login;
import server.persistence.HibernateUtil;

public class LoginQuery {
	String password = null;
	
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
	
}
