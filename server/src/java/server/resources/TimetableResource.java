package server.resources;

import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import server.entities.Timetable;
import server.entities.TimetableLesson;
import server.persistence.HibernateUtil;

import com.google.gson.Gson;

// TODO
@Path("timetable")
public class TimetableResource {

	@GET
	@Path("class")
	@Produces(MediaType.APPLICATION_JSON)
	public String getClassTimetableJSON(@QueryParam("id") int classId) {
		final Gson gson = new Gson();
		final String json = gson.toJson(getClassTimetable(classId));
		return json;
	}

	@GET
	@Path("room")
	@Produces(MediaType.APPLICATION_JSON)
	public String getRoomTimetableJSON(@QueryParam("id") int roomId) {
		final Gson gson = new Gson();
		final String json = gson.toJson(getRoomTimetable(roomId));
		return json;
	}

	@GET
	@Path("teacher")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTeacherTimetableJSON(@QueryParam("id") int teacherId) {
		final Gson gson = new Gson();
		final String json = gson.toJson(getTeacherTimetable(teacherId));
		return json;
	}

	@SuppressWarnings("unchecked")
	public Timetable getClassTimetable(int classId) {
		String sql = "select * from klasse_tag_stunde where idklasse = '"
				+ classId + "'";
		Query query = HibernateUtil.getEntityManager().createNativeQuery(sql,
				TimetableLesson.class);
		Timetable timetable = new Timetable();
		timetable.setLessons(query.getResultList());
		return timetable;
	}

	@SuppressWarnings("unchecked")
	public Timetable getRoomTimetable(int roomId) {
		String sql = "select * from klasse_tag_stunde where idRaum = '"
				+ roomId + "'";
		Query query = HibernateUtil.getEntityManager().createNativeQuery(sql,
				TimetableLesson.class);
		Timetable timetable = new Timetable();
		timetable.setLessons(query.getResultList());
		return timetable;
	}

	@SuppressWarnings("unchecked")
	public Timetable getTeacherTimetable(int teacherId) {
		String sql = "select * from klasse_tag_stunde where idLehrer = '"
				+ teacherId + "'";
		Query query = HibernateUtil.getEntityManager().createNativeQuery(sql,
				TimetableLesson.class);
		Timetable timetable = new Timetable();
		timetable.setLessons(query.getResultList());
		return timetable;
	}
}
