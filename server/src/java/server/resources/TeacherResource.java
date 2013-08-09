package server.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import server.entities.Teacher;
import server.persistence.TeacherTest;

import com.google.gson.Gson;

@Path("teacher")
public class TeacherResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getFormsJSON() {
		Teacher teacher = TeacherTest.getTeacher();
		Gson gson = new Gson();
		String json = gson.toJson(teacher);
		return json;
	}

}
