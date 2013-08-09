package server.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import server.entities.Form;
import server.persistence.HibernateUtil;

import com.google.gson.Gson;

@Path("class")
public class ClassResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getClassesJSON() {
		List<Form> classList = HibernateUtil.getEntityManager().createQuery("select p from Klasse p").getResultList();
		Gson gson = new Gson();
		String json = gson.toJson(classList);
		return json;
	}
}
