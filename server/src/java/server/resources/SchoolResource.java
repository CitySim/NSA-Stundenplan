package server.resources;

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
	public String getFormsJSON() {
		Gson gson = new Gson();
		String json = gson.toJson(getSchool());
		return json;
	}

	private School getSchool() {
		return HibernateUtil.getEntityManager().find(School.class, 1);
	}
}