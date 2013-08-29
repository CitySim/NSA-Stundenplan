package server.resources;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import server.entities.Replacement;
import server.entities.TimetableLesson;
import server.persistence.HibernateUtil;

import com.google.gson.Gson;

@Path("replacement")
public class ReplacementResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getReplacementJSON(@QueryParam("teacher") final int teacherId, @QueryParam("room") final int roomId,
			@QueryParam("class") final int formId, @QueryParam("start") final Date start, @QueryParam("end") final Date end) {
		final Gson gson = new Gson();
		final String json = gson.toJson(this.getReplacements(teacherId, formId, roomId, start, end));
		return json;
	}

	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addReplacementJSON(String replacementJSON, @QueryParam("lesson") final int lessonId,
			@QueryParam("form") final int formId, @QueryParam("day") final int dayId) {
		final Gson gson = new Gson();
		final Replacement replacement = gson.fromJson(replacementJSON, Replacement.class);
		return new Gson().toJson(this.addReplacement(replacement, lessonId, formId, dayId));
	}

	private Replacement addReplacement(final Replacement replacement, final int lessonId, final int formId, final int dayId) {
		final EntityManager entityManager = HibernateUtil.getEntityManager();
		final Session session = entityManager.unwrap(Session.class);

		final Criteria criteria = session.createCriteria(TimetableLesson.class);
		criteria.add(Restrictions.eq("lesson.id", lessonId));
		criteria.add(Restrictions.eq("form.id", formId));
		criteria.add(Restrictions.eq("day.id", dayId));
		final TimetableLesson timetableLesson = (TimetableLesson) criteria.uniqueResult();

		entityManager.getTransaction().begin();
		entityManager.persist(replacement);
		timetableLesson.setReplacement(replacement);
		entityManager.refresh(timetableLesson);
		entityManager.getTransaction().commit();
		return replacement;
	}

	private List<?> getReplacements(final int teacherId, final int formId, final int roomId, final Date start, final Date end) {
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
		if (start != null && end != null) {
			criteria.add(Restrictions.between("date", start, end));
		}
		return criteria.list();
	}
}