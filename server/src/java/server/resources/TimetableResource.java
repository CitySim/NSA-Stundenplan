package server.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import server.entities.Timetable;
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
	public List<Timetable> getClassTimetable(int classId) {
		return HibernateUtil
				.getEntityManager()
				.createNativeQuery("select * from timetable",
						Timetable.class).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Timetable> getRoomTimetable(int roomId) {
		return HibernateUtil
				.getEntityManager()
				.createNativeQuery("select * from timetable",
						Timetable.class).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Timetable> getTeacherTimetable(int teacherId) {
		return HibernateUtil
				.getEntityManager()
				.createNativeQuery("select * from timetable",
						Timetable.class).getResultList();
	}
}
