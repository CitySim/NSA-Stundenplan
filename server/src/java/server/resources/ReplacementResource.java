package server.resources;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
import server.entities.TimetableLesson;
import server.persistence.HibernateUtil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("replacement")
public class ReplacementResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getReplacementJSON(@QueryParam("teacher") final int teacherId, @QueryParam("room") final int roomId,
			@QueryParam("class") final int formId, @QueryParam("start") final String week) {
		return new Gson().toJson(this.getReplacements(teacherId, formId, roomId, week));
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public final synchronized String changeReplacementJSON(@CookieParam(value = "NSA-Cookie") final String nsaCookie, final String replacementJSON) {
		// TODO: check nsaCookie
		try {
			final GsonBuilder gson = new GsonBuilder();
			gson.registerTypeAdapter(Replacement.class, new ReplacementDeserializer());
			final Replacement replacement = gson.create().fromJson(replacementJSON, Replacement.class);
			final EntityManager entityManager = HibernateUtil.getEntityManager();
			entityManager.getTransaction().begin();
			HibernateUtil.getEntityManager().persist(replacement);
			entityManager.getTransaction().commit();
			return new Gson().toJson(replacement);
		} catch (final NullPointerException e) {
			return this.changeReplacementJSON(nsaCookie, replacementJSON);
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public final synchronized String addReplacementJSON(@CookieParam(value = "NSA-Cookie") final String nsaCookie, final String replacementJSON) {
		// TODO: check nsaCookie
		try {
			final GsonBuilder gson = new GsonBuilder();
			gson.registerTypeAdapter(Replacement.class, new ReplacementDeserializer());
			final Replacement replacement = gson.create().fromJson(replacementJSON, Replacement.class);

			return new Gson().toJson(this.addReplacement(replacement));
		} catch (final NullPointerException e) {
			return this.addReplacementJSON(nsaCookie, replacementJSON);
		}
	}

	@DELETE
	public final synchronized boolean deleteReplacement(@CookieParam(value = "NSA-Cookie") final String nsaCookie,
			@QueryParam("id") final int replacementId) {
		// TODO: check nsaCookie
		try {
			final EntityManager entityManager = HibernateUtil.getEntityManager();
			final Replacement replacement = entityManager.find(Replacement.class, replacementId);
			if (replacement == null) {
				return false;
			}
			final String sql = "select * from klasse_tag_stunde where replacement_id = " + replacementId;
			final Query query = entityManager.createNativeQuery(sql, TimetableLesson.class);
			final TimetableLesson timetableLesson = (TimetableLesson) query.getResultList().get(0);
			if (timetableLesson != null) {
				entityManager.getTransaction().begin();
				entityManager.persist(timetableLesson);
				entityManager.getTransaction().commit();
			}
			entityManager.getTransaction().begin();
			entityManager.remove(replacement);
			entityManager.getTransaction().commit();
			return true;
		} catch (final NullPointerException e) {
			return this.deleteReplacement(nsaCookie, replacementId);
		}
	}

	private synchronized Replacement addReplacement(final Replacement replacement) {
		final EntityManager entityManager = HibernateUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(replacement);
			entityManager.getTransaction().commit();
			return replacement;
		} catch (final NullPointerException e) {
			return this.addReplacement(replacement);
		}
	}

	private synchronized List<?> getReplacements(final int teacherId, final int formId, final int roomId, final String week) {
		try {
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
		} catch (final NullPointerException e) {
			return this.getReplacements(teacherId, formId, roomId, week);
		}
	}
}