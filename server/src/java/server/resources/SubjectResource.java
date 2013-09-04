package server.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import server.entities.Subject;
import server.persistence.HibernateUtil;

import com.google.gson.Gson;

@Path("subject")
public class SubjectResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getSubjectsJSON() {
		return new Gson().toJson(this.getSubjects());
	}

	@SuppressWarnings("unchecked")
	public synchronized List<Subject> getSubjects() {
		return HibernateUtil.getEntityManager().createNativeQuery("select * from Unterrichtsfach", Subject.class).getResultList();
	}
}