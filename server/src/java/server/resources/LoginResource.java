package server.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import server.exceptions.EmailAddressException;
import server.exceptions.EmailSendingException;
import server.exceptions.LoginFailedException;
import server.operations.AccountHandler;
import server.operations.CookieHandler;
import server.operations.LoginValidator;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Path("login")
public class LoginResource {

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response doLogin(@DefaultValue("") @FormParam("user") final String userName, @DefaultValue("") @FormParam("password") final String password) {
		final Gson gson = new Gson();
		String json;
		NewCookie cookie = null;

		try {
			new LoginValidator().validateLoginData(userName, password);
			cookie = new CookieHandler().createCookie();

			final JsonObject loginResult = new JsonObject();
			loginResult.addProperty("status", "ok");

			json = gson.toJson(loginResult);
		} catch (final LoginFailedException e) {
			final JsonObject loginResult = new JsonObject();
			loginResult.addProperty("status", "error");
			loginResult.addProperty("error", e.getMessage());

			json = gson.toJson(loginResult);
		}
		return Response.ok(json).cookie(cookie).build();
	}

	@Path("resetpw")
	@GET
	public String resetPassword(@QueryParam("user") final String userName) {
		final AccountHandler accountHandler = new AccountHandler();
		boolean result = false;
		try {
			result = accountHandler.resetPassword(userName);
		} catch (final EmailSendingException e) {
			return new Gson().toJson(e.getMessage());
		} catch (final EmailAddressException e) {
			return new Gson().toJson(e.getMessage());
		}
		return new Gson().toJson(result);
	}

	@Path("changepw")
	@GET
	public String changePassword(@QueryParam("user") final int userId) {
		try {
			new AccountHandler().changePassword(userId);
		} catch (final EmailSendingException | EmailAddressException e) {
			return new Gson().toJson(e.getMessage());
		}
		return "Ihr Passwort wurde erfolgreich geändert und wird Ihnen per Mail zugesendet";
	}

	@Path("logout")
	@GET
	public String logout(@QueryParam("cookie") final String cookie) {
		return new Gson().toJson(new CookieHandler().deleteCookie(cookie));
	}
}