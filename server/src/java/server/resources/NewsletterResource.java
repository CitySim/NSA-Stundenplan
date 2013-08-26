package server.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import server.entities.Form;
import server.entities.Newsletter;
import server.operations.NewsLetterHandler;
import server.queries.QueryResult;

import com.google.gson.Gson;

@Path("newsletter")
public class NewsletterResource extends QueryResult {

	@GET
	@Path("confirm")
	@Produces(MediaType.APPLICATION_JSON)
	public String confirmRegistration(@QueryParam("id") int formId, @QueryParam("email") final String email) {
		NewsLetterHandler newsLetterHandler = new NewsLetterHandler();
		Form form = em.find(Form.class, formId);
		if (form == null) {
			// FIXME fehler zurückgeben
		}
		boolean result = newsLetterHandler.confirmRegistration(form, email);
		final Gson gson = new Gson();
		String json = gson.toJson(result);
		return json;
	}

	@GET
	@Path("remove")
	@Produces(MediaType.APPLICATION_JSON)
	public String removeRegistration(@QueryParam("id") int newsletterId) {
		NewsLetterHandler newsLetterHandler = new NewsLetterHandler();
		Newsletter newsletter = em.find(Newsletter.class, newsletterId);
		boolean result = newsLetterHandler.removeRegistration(newsletter);
		final Gson gson = new Gson();
		String json = gson.toJson(result);
		return json;
	}
}