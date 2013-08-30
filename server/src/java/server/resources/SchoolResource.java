package server.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

@Path("school")
public class SchoolResource {
	School school = new School();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getFormsJSON() {
		Gson gson = new Gson();
		String json = gson.toJson(school);
		return json;
	}

	// TODO: Dummy muss in ie Datenbank
	private class School {
		@SuppressWarnings("unused")
		String image = "http://mw2.google.com/mw-panoramio/photos/medium/24678566.jpg";
		@SuppressWarnings("unused")
		String text = "++++ Neuer Rekord: Die IT1a hat den besten Notenschnitt, den jemals eine Klasse erreicht hat ++++<br>"
				+ "++++ Zeugnisse sind fertig ++++<br>"
				+ "++++ Studie: Killerspiele fördern Aufmerksamkeit und Reaktionsvermögen ++++<br>"
				+ "++++ Folgende Lehrer sind krank: ... ++++<br>"
				+ "++++ Stundenausfall für die Klassen IT3C, CH3M, IN5T ++++<br>"
				+ "++++ Studie: Lernortverlegung nach Zuhause führt zu effektiverem Lernverhalten ++++<br>";
	}
}