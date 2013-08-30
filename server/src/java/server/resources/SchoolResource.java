package server.resources;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import server.entities.School;
import server.persistence.HibernateUtil;

import com.google.gson.Gson;

@Path("school")
public class SchoolResource {
	School school = new School();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getSchoolJSON() {
		Gson gson = new Gson();
		String json = gson.toJson(getSchool());
		return json;
	}

	
	public void changeSchoolJSON(){
	}
	
	private School getSchool() {
		return HibernateUtil.getEntityManager().find(School.class, 1);
	}

	private void setSchool(String image, String text) {
		EntityManager em = HibernateUtil.getEntityManager();
		school = em.find(School.class, 1);
		em.getTransaction().begin();
		if (image != null) {
			school.setImage(image);
		}
		if (text != null) {
			school.setText(text);
		}
		em.persist(school);
		em.getTransaction().commit();

	}
}