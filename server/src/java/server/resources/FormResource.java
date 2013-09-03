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
		return new Gson().toJson(FormResource.getForms());
	}

	@SuppressWarnings("unchecked")
	public synchronized static List<Form> getForms() {
		try {
			return HibernateUtil.getEntityManager().createNativeQuery("select * from Klasse", Form.class).getResultList();
		} catch (final NullPointerException e) {
			return FormResource.getForms();
		}
	}
}