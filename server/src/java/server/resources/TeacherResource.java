package server.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import server.entities.Teacher;
import server.persistence.HibernateUtil;

import com.google.gson.Gson;

@Path("teacher")
public class TeacherResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getFormsJSON() {
		return new Gson().toJson(this.getTeachers());
	}

	@SuppressWarnings("unchecked")
	public synchronized List<Teacher> getTeachers() {
		return HibernateUtil.getEntityManager().createNativeQuery("select * from lehrer", Teacher.class).getResultList();
	}
}