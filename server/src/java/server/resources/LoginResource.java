package server.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import server.exceptions.LoginFailedException;
import server.operations.CookieServlet;
import server.operations.LoginValidator;

import com.google.gson.Gson;

@Path("login")
public class LoginResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String doLogin() {
		final Gson gson = new Gson();
		final String json;
		if (this.checkLogin("a", "b")) {
			json = gson.toJson(new CookieServlet().createCookie());
		} else {
			json = "Fehler";
		}
		return json;
	}

	public boolean checkLogin(final String userName, final String password) {
		try {
			return new LoginValidator().validateLoginData(userName, password);
		} catch (final LoginFailedException e) {
			return false;
		}
	}
}
