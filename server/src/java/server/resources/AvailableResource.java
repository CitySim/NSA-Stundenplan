package server.resources;

import java.util.List;

import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import server.entities.Room;
import server.entities.Teacher;
import server.persistence.HibernateUtil;

import com.google.gson.Gson;

@Path("available")
public class AvailableResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAvailable(@QueryParam("day") int dayId, @QueryParam("lesson") int lessonId) {
		Available available = new Available();
		available.setAvailableTeachers(getAvailableTeachers(dayId, lessonId));
		available.setAvailableRooms(getAvailableRooms(dayId, lessonId));
		return new Gson().toJson(available);
	}

	@SuppressWarnings("unchecked")
	private List<Teacher> getAvailableTeachers(int dayId, int lessonId) {
		String sql = "select * from lehrer where idLehrer not in " + "(select idLehrer " + "from klasse_tag_stunde where idTag = 1 and idStunde = 1)";
		Query query = HibernateUtil.getEntityManager().createNativeQuery(sql, Teacher.class);

		List<Teacher> liste = query.getResultList();
		return liste;
	}

	@SuppressWarnings("unchecked")
	private List<Room> getAvailableRooms(int dayId, int lessonId) {
		String sql = "select * from raum where idRaum not in " + "(select idRaum " + "from klasse_tag_stunde where idTag = 1 and idStunde = 1)";
		Query query = HibernateUtil.getEntityManager().createNativeQuery(sql, Room.class);

		List<Room> liste = query.getResultList();
		return liste;
	}

	private class Available {
		@SuppressWarnings("unused")
		public List<Teacher> availableTeachers;
		@SuppressWarnings("unused")
		private List<Room> availableRooms;

		private void setAvailableTeachers(List<Teacher> availableTeachers) {
			this.availableTeachers = availableTeachers;
		}

		private void setAvailableRooms(List<Room> availableRooms) {
			this.availableRooms = availableRooms;
		}
	}
}