package server.resources;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
import com.google.gson.JsonObject;

@Path("replacement")
public class ReplacementResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getReplacementJSON(
			@QueryParam("teacher") int teacherId,
			@QueryParam("room") int roomId,
			@QueryParam("class") int formId,
			@QueryParam("start") Date start,
			@QueryParam("end") Date end) {
		final Gson gson = new Gson();
		final String json = gson.toJson(getReplacements(teacherId, formId, roomId, start, end));
		return json;
	}

	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addReplacementJSON(@PathParam("replacement") String replacementJSON,
			@QueryParam("lesson") int lessonId,
			@QueryParam("form") int formId,
			@QueryParam("day") int dayId) {
		Gson gson = new Gson();
		Replacement replacement = gson.fromJson(replacementJSON, Replacement.class);
		return new Gson().toJson(addReplacement(replacement, lessonId, formId, dayId));
	}

	private Replacement addReplacement(Replacement replacement, int lessonId, int formId, int dayId) {
		EntityManager entityManager = HibernateUtil.getEntityManager();
		Session session = entityManager.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(TimetableLesson.class);
		criteria.add(Restrictions.eq("lesson.id", lessonId));
		criteria.add(Restrictions.eq("form.id", formId));
		criteria.add(Restrictions.eq("day.id", dayId));	
		TimetableLesson timetableLesson = (TimetableLesson) criteria.uniqueResult();
		
		entityManager.getTransaction().begin();
		entityManager.persist(replacement);
		timetableLesson.setReplacement(replacement);
		entityManager.persist(timetableLesson);
		entityManager.getTransaction().commit();
		return replacement;
	}

	private List<?> getReplacements(int teacherId, int formId, int roomId, Date start, Date end) {
		Session session = HibernateUtil.getEntityManager().unwrap(Session.class);
		Criteria criteria = session.createCriteria(Replacement.class);
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