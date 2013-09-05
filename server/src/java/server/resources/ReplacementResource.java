package server.resources;

import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import server.entities.Replacement;
import server.entities.ReplacementDeserializer;
import server.exceptions.CookieInvalidException;
import server.exceptions.EmailAddressException;
import server.exceptions.EmailSendingException;
import server.exceptions.ScheduleCreationException;
import server.operations.CookieHandler;
import server.operations.NewsLetterHandler;
import server.operations.email.EmailJobHelper;
import server.persistence.HibernateUtil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

@Path("replacement")
public class ReplacementResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getReplacementJSON(@QueryParam("teacher") final int teacherId, @QueryParam("room") final int roomId,
			@QueryParam("class") final int formId, @QueryParam("week") final String week) {
		return new Gson().toJson(this.getReplacements(teacherId, formId, roomId, week));
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public final String changeReplacementJSON(@CookieParam(value = "NSA-Cookie") final String nsaCookie, final String replacementJSON) {
		try {
			if (new CookieHandler().validateCookie(nsaCookie)) {
				final GsonBuilder gson = new GsonBuilder();
				gson.registerTypeAdapter(Replacement.class, new ReplacementDeserializer());
				final Replacement replacement = gson.create().fromJson(replacementJSON, Replacement.class);
				final EntityManager entityManager = HibernateUtil.getEntityManager();
				entityManager.getTransaction().begin();
				HibernateUtil.getEntityManager().persist(replacement);
				entityManager.getTransaction().commit();
				new NewsLetterHandler().generateReplacementMail(replacement);

				return new Gson().toJson(replacement);
			}
		} catch (JsonSyntaxException | CookieInvalidException | ScheduleCreationException | EmailSendingException | EmailAddressException e) {
			return new Gson().toJson(e.getMessage());
		}
		return null;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public final String addReplacementJSON(@CookieParam(value = "NSA-Cookie") final String nsaCookie, final String replacementJSON) {
		try {
			if (new CookieHandler().validateCookie(nsaCookie)) {
				final GsonBuilder gson = new GsonBuilder();
				gson.registerTypeAdapter(Replacement.class, new ReplacementDeserializer());
				final Replacement replacement = gson.create().fromJson(replacementJSON, Replacement.class);
				new NewsLetterHandler().generateReplacementMail(replacement);
				return new Gson().toJson(this.addReplacement(replacement));
			}
		} catch (JsonSyntaxException | CookieInvalidException | ScheduleCreationException | EmailSendingException | EmailAddressException e) {
			return new Gson().toJson(e.getMessage());
		}
		return null;
	}

	@DELETE
	public final boolean deleteReplacement(@CookieParam(value = "NSA-Cookie") final String nsaCookie, @QueryParam("id") final int replacementId) {
		try {
			if (new CookieHandler().validateCookie(nsaCookie)) {
				final EntityManager entityManager = HibernateUtil.getEntityManager();
				final Replacement replacement = entityManager.find(Replacement.class, replacementId);
				if (replacement == null) {
					return false;
				}

				entityManager.getTransaction().begin();
				entityManager.remove(replacement);
				entityManager.getTransaction().commit();
				new NewsLetterHandler().generateReplacementMail(replacement);

				return true;
			}
		} catch (final CookieInvalidException | ScheduleCreationException | EmailSendingException | EmailAddressException e) { // TODO
																																// Exception
																																// Handling
																																// SVEN!??
			return false;
		}
		return false;
	}

	private Replacement addReplacement(final Replacement replacement) {
		final EntityManager entityManager = HibernateUtil.getEntityManager();

		entityManager.getTransaction().begin();
		entityManager.persist(replacement);
		entityManager.getTransaction().commit();
		return replacement;
	}

	private List<?> getReplacements(final int teacherId, final int formId, final int roomId, final String week) {
		final Session session = HibernateUtil.getEntityManager().unwrap(Session.class);
		final Criteria criteria = session.createCriteria(Replacement.class);

		if (teacherId != 0) {
			criteria.add(Restrictions.eq("teacher.id", teacherId));
		}
		if (formId != 0) {
			criteria.add(Restrictions.eq("form.id", formId));
		}
		if (roomId != 0) {
			criteria.add(Restrictions.eq("room.id", roomId));
		}
		if (week != null) {
			criteria.add(Restrictions.eq("week", week));
		}

		return criteria.list();
	}
}