package server.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import server.entities.Form;
import server.operations.NewsLetterHandler;
import server.queries.QueryResult;

import com.google.gson.Gson;

@Path("newsletter")
public class NewsletterResource extends QueryResult {

	@GET
	@Path("confirm")
	@Produces(MediaType.APPLICATION_JSON)
	public String confirmAccount(@QueryParam("id") int formId, @QueryParam("email") final String email) {
		NewsLetterHandler newsLetterHandler = new NewsLetterHandler();
		Form form = em.find(Form.class, formId);
		if (form == null) {
			//FIXME fehler zurückgeben
		}
		boolean result = newsLetterHandler.confirmRegistration(form, email);
		final Gson gson = new Gson();
		String json = gson.toJson(result);
		return json;
	}
	
	@GET
	@Path("remove")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteAccount(@QueryParam("id") int userId) {
		final Gson gson = new Gson();
		String json = null;
		return json;
	}
}