package server.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import server.entities.Timetable;
import server.persistence.HibernateUtil;

import com.google.gson.Gson;

// TODO
@Path("timetable")
public class TimetableResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getTimetablesJSON() {
		final Gson gson = new Gson();
		final String json = gson.toJson(getTimetable());
		return json;
	}

	@SuppressWarnings("unchecked")
	public List<Timetable> getTimetable() {
		return HibernateUtil
				.getEntityManager()
				.createNativeQuery("select * from timetable",
						Timetable.class).getResultList();
	}
}
