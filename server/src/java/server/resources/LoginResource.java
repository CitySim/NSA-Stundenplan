package server.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import server.operations.LoginValidator;

@Path("login")
public class LoginResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String doLogin() {
		final Gson gson = new Gson();
		final String json = gson.toJson("");
		return json;
	}
	
	public void checkLogin(String userName, String password) {
		LoginValidator a = new LoginValidator();
		a.validateLoginData(userName, password);
	}
	
	public void createCookie(String userName, String password) {
		
	}
}
