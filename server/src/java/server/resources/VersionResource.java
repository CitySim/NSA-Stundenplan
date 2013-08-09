package server.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import server.entities.Form;
import server.persistence.HibernateUtil;

import com.google.gson.Gson;

@Path("version")
public class VersionResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getVersionJSON() {
		// TODO Version an anderer Stelle ablegen
		String version = "0.0.1";
		Gson gson = new Gson();
		String json = gson.toJson(version);
		return json;
	}
	
}
