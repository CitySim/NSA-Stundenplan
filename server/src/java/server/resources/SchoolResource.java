package server.resources;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import server.entities.School;
import server.persistence.HibernateUtil;

import com.google.gson.Gson;

@Path("school")
public class SchoolResource {
	School newSchool = new School();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getSchoolJSON() {
		Gson gson = new Gson();
		String json = gson.toJson(getSchool());
		return json;
	}

	public void changeSchoolJSON() {
	}

	private School getSchool() {
		return HibernateUtil.getEntityManager().find(School.class, 1);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void setSchool(String schoolJSON) {

		Gson gson = new Gson();
		School school = gson.fromJson(schoolJSON, School.class);

		EntityManager em = HibernateUtil.getEntityManager();
		newSchool = em.find(School.class, 1);
		em.getTransaction().begin();
		newSchool.setImage(school.getImage());
		newSchool.setText(school.getText());
		em.persist(newSchool);
		em.getTransaction().commit();

	}
}