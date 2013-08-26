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
public class FormResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getFormsJSON() {
		final List<Form> list = getForms();
		final Gson gson = new Gson();
		final String json = gson.toJson(list);
		return json;
	}

	@SuppressWarnings("unchecked")
	public static List<Form> getForms() {
		return HibernateUtil.getEntityManager().createNativeQuery("select * from Klasse", Form.class).getResultList();
	}
}