package server.resources;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import server.exceptions.LoginFailedException;
import server.operations.CookieValidator;
import server.operations.LoginValidator;

import com.google.gson.Gson;

@Path("login")
public class LoginResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String doLogin(
			@DefaultValue("") @QueryParam("user") String userName,
			@DefaultValue("") @QueryParam("password") String password) {
		final Gson gson = new Gson();
		String json;
		try {
			new LoginValidator().validateLoginData(userName, password);
			json = gson.toJson(new CookieValidator().createCookie());

		} catch (LoginFailedException e) {
			json = gson.toJson(e.getMessage());
		}
		return json;
	}
}