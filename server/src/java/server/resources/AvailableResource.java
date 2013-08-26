package server.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import server.entities.Room;
import server.entities.Teacher;

import com.google.gson.Gson;

@Path("available")
public class AvailableResource {
	
	// FIXME Json richtig zusammen setzten, querys implementieren
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAvailable() {
		final Gson gson = new Gson();
		String json = gson.toJson(getAvailableTeachers());
		json = json + gson.toJson(getAvailableRooms());
		return json;
	}

	private List<Teacher> getAvailableTeachers() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private List<Room> getAvailableRooms() {
		// TODO Auto-generated method stub
		return null;
	}

}