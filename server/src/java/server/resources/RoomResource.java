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
		return new Gson().toJson(this.getRooms());
	}

	@SuppressWarnings("unchecked")
	public List<Room> getRooms() {
		return HibernateUtil.getEntityManager().createNativeQuery("select * from Raum", Room.class).getResultList();
	}
}