package server.resources;

import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import server.entities.Timetable;
import server.persistence.HibernateUtil;

import com.google.gson.Gson;

@Path("timetable")
public class TimetableResource {

	@GET
	@Path("class")
	@Produces(MediaType.APPLICATION_JSON)
	public String getClassTimetableJSON(@QueryParam("id") final int classId) {
		return new Gson().toJson(this.getClassTimetable(classId));
	}

	@GET
	@Path("room")
	@Produces(MediaType.APPLICATION_JSON)
	public String getRoomTimetableJSON(@QueryParam("id") final int roomId) {
		return new Gson().toJson(this.getRoomTimetable(roomId));
	}

	@GET
	@Path("teacher")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTeacherTimetableJSON(@QueryParam("id") final int teacherId) {
		return new Gson().toJson(this.getTeacherTimetable(teacherId));
	}

	public Timetable getClassTimetable(final int formId) {
		final String timetableSql = "select * from timetable where form_idKlasse = '" + formId + "'";
		final Query timetableQuery = HibernateUtil.getEntityManager().createNativeQuery(timetableSql, Timetable.class);

		if (timetableQuery.getResultList().size() > 0) {
			return (Timetable) timetableQuery.getResultList().get(0);
		}
		return null;
	}

	private Timetable getRoomTimetable(final int roomId) {
		final String timetableSql = "select * from timetable where room_idRaum = '" + roomId + "'";
		final Query timetableQuery = HibernateUtil.getEntityManager().createNativeQuery(timetableSql, Timetable.class);

		if (timetableQuery.getResultList().size() > 0) {
			return (Timetable) timetableQuery.getResultList().get(0);
		}
		return null;
	}

	private Timetable getTeacherTimetable(final int teacherId) {
		final String timetableSql = "select * from timetable where teacher_idLehrer = '" + teacherId + "'";
		final Query timetableQuery = HibernateUtil.getEntityManager().createNativeQuery(timetableSql, Timetable.class);

		if (timetableQuery.getResultList().size() > 0) {
			return (Timetable) timetableQuery.getResultList().get(0);
		}
		return null;
	}

	protected Timetable getTimetable(final int timetableId) {
		final String sql = "select * from timetable where id = '" + timetableId + "'";
		final Query query = HibernateUtil.getEntityManager().createNativeQuery(sql, Timetable.class);
		final Timetable timetable = (Timetable) query.getResultList().get(0);
		return timetable;
	}
}