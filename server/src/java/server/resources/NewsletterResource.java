package server.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import server.entities.Form;
import server.entities.Newsletter;
import server.exceptions.EmailAddressException;
import server.exceptions.EmailSendingException;
import server.operations.NewsLetterHandler;
import server.operations.email.EmailJobHelper;
import server.queries.QueryResult;

import com.google.gson.Gson;

@Path("newsletter")
public class NewsletterResource extends QueryResult {

	@GET
	@Path("confirm")
	@Produces(MediaType.APPLICATION_JSON)
	public final synchronized String confirmRegistration(@QueryParam("id") final int formId, @QueryParam("email") final String email) {
		final NewsLetterHandler newsLetterHandler = new NewsLetterHandler();
		final Form form = this.em.find(Form.class, formId);
		if (form == null) {
			return new Gson().toJson("Fehler: Klasse nicht gefunden");
		}
		try {
			final EmailJobHelper helper = new EmailJobHelper();
			helper.sendConfirmationMail(form, email);

			return new Gson().toJson(newsLetterHandler.confirmRegistration(form, email));
		} catch (final EmailSendingException e) {
			e.printStackTrace();
		} catch (final EmailAddressException e) {
			e.printStackTrace();
		}
		return null;
	}

	@GET
	@Path("remove")
	@Produces(MediaType.APPLICATION_JSON)
	public synchronized String removeRegistration(@QueryParam("id") final int newsletterId) {
		final NewsLetterHandler newsLetterHandler = new NewsLetterHandler();
		final Newsletter newsletter = this.em.find(Newsletter.class, newsletterId);
		return new Gson().toJson(newsLetterHandler.removeRegistration(newsletter));
	}
}