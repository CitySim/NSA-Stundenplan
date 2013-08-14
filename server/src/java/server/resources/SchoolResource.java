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
	private class School{
		@SuppressWarnings("unused")
		String image = "http://i.imgur.com/xcEPAMC.gif";
		@SuppressWarnings("unused")
		String text = "++++ Einfach mal abschalten: Pfleger geniesst Ruhe auf der Intensivstation ++++<br>"
				+ "++++ Er kann sich ins Knie ficken: Frau will mit Schlangenmenschen nicht ins Bett ++++<br>"
				+ "++++ Nicht die feine Art: Kundin schreit Metzgerin wegen falscher Leberwurst an ++++<br>"
				+ "++++ Gehen langsam aus: Alte Gigolos ++++<br>"
				+ "++++ Hier isst der Kunde K�nig: Kannibalen-Restaurant serviert H�uptlingsfleisch ++++<br>"
				+ "++++ Den Dicken markiert: Paintballprofi prahlt nach Sieg �ber korpulenten Gegner ++++<br>"
				+ "++++ Trachten nach dem Tod: Bayerische Profikiller m�chten in traditioneller Kleidung bestattet werden ++++";
	}
}

