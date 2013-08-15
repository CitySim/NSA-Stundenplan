package server.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import server.entities.Lesson;
import server.persistence.HibernateUtil;

import com.google.gson.Gson;

@Path("lesson")
public class LessonResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getLessonsJSON() {
		final Gson gson = new Gson();
		final String json = gson.toJson(getLessons());
		return json;
	}

	@SuppressWarnings("unchecked")
	public List<Lesson> getLessons() {
		return HibernateUtil.getEntityManager()
				.createNativeQuery("select * from Stunde", Lesson.class)
				.getResultList();
	}
}
