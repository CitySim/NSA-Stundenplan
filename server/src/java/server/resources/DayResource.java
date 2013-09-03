package server.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import server.entities.Day;
import server.persistence.HibernateUtil;

import com.google.gson.Gson;

@Path("day")
public class DayResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getDaysJSON() {
		final Gson gson = new Gson();
		final String json = gson.toJson(this.getDays());
		return json;
	}

	@SuppressWarnings("unchecked")
	public synchronized List<Day> getDays() {
		return HibernateUtil.getEntityManager().createNativeQuery("select * from Tag", Day.class).getResultList();
	}
}