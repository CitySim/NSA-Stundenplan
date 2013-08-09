package server.entities;

import javax.persistence.Entity;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

@Path("class")
@Entity
public class Klasse extends DefaultEntity {
		
	private String bezeichung;
	
	public String getBezeichung() {
		return this.bezeichung;
	}

	public void setBezeichung(final String bezeichung) {
		this.bezeichung = bezeichung;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getJSON() {
		this.setBezeichung("IT1A");
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return json;
	}	
}
