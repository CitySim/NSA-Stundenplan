package server.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import server.operations.CookieServlet;
import server.operations.LoginValidator;

@Path("login")
public class LoginResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String doLogin() {
		final Gson gson = new Gson();
		final String json;
		if (checkLogin("a", "b")) {
			json = gson.toJson(new CookieServlet().createCookie());
		} else {
			json = "Fehler";
		}
		return json;
	}
	
	public boolean checkLogin(String userName, String password) {
		return new LoginValidator().validateLoginData(userName, password);
	}
}
