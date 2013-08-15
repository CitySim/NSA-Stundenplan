package server.queries;

import java.util.List;

import server.entities.Login;
import server.persistence.HibernateUtil;

public class LoginQuery {
	
	public String getPassword(String username){
		List<Login> loginList =  HibernateUtil.getEntityManager()
					.createNativeQuery("select * from Login", Login.class)
					.getResultList();
		return null;

	}
	
}
