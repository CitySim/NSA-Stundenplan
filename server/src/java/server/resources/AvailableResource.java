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
	public final String getAvailable(@QueryParam("day") final int dayId, @QueryParam("lesson") final int lessonId) {
		if (dayId == 0 || lessonId == 0) {
			return new Gson().toJson("Ungültiger Tag oder Stunde ausgewählt!");
		}
		final Available available = new Available();
		available.setAvailableTeachers(this.getAvailableTeachers(dayId, lessonId));
		available.setAvailableRooms(this.getAvailableRooms(dayId, lessonId));
		return new Gson().toJson(available);
	}

	@SuppressWarnings("unchecked")
	private synchronized List<Teacher> getAvailableTeachers(final int dayId, final int lessonId) {
		final String sql = "select * from lehrer where idLehrer not in " + "(select idLehrer " + "from klasse_tag_stunde where idTag = " + dayId
				+ " and idStunde = " + lessonId + ")";
		final Query query = HibernateUtil.getEntityManager().createNativeQuery(sql, Teacher.class);

		final List<Teacher> liste = query.getResultList();
		return liste;
	}

	@SuppressWarnings("unchecked")
	private synchronized List<Room> getAvailableRooms(final int dayId, final int lessonId) {
		final String sql = "select * from raum where idRaum not in " + "(select idRaum " + "from klasse_tag_stunde where idTag = " + dayId
				+ " and idStunde = " + lessonId + ")";
		final Query query = HibernateUtil.getEntityManager().createNativeQuery(sql, Room.class);

		final List<Room> liste = query.getResultList();
		return liste;
	}

	private class Available {
		@SuppressWarnings("unused")
		private List<Teacher> availableTeachers;
		@SuppressWarnings("unused")
		private List<Room> availableRooms;

		private void setAvailableTeachers(final List<Teacher> availableTeachers) {
			this.availableTeachers = availableTeachers;
		}

		private void setAvailableRooms(final List<Room> availableRooms) {
			this.availableRooms = availableRooms;
		}
	}
}