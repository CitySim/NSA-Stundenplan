package server.resources;

import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import server.entities.Replacement;
import server.persistence.HibernateUtil;

import com.google.gson.Gson;

@Path("replacement")
public class ReplacementResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getReplacementJSON(
			@QueryParam("teacher") int teacherId,
			@QueryParam("room") int roomId,
			@QueryParam("class") int formId,
			@QueryParam("start") Date start,
			@QueryParam("end") Date end)
	{
		final Gson gson = new Gson();
		final String json = gson.toJson(getReplacements(teacherId, formId, roomId, start, end));
		return json;
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
