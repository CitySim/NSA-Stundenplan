package server.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import server.entities.Room;
import server.persistence.HibernateUtil;

import com.google.gson.Gson;

@Path("room")
public class RoomResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getRoomsJSON() {
		final Gson gson = new Gson();
		final String json = gson.toJson(this.getRooms());
		return json;
	}

	@SuppressWarnings("unchecked")
	public synchronized List<Room> getRooms() {
		try {
			return HibernateUtil.getEntityManager().createNativeQuery("select * from Raum", Room.class).getResultList();
		} catch (final NullPointerException e) {
			return this.getRooms();
		}
	}
}