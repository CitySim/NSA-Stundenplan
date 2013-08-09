package server.webservices;

import server.entities.Form;

import com.google.gson.Gson;

public class Webservices {
	
	public static Form receive(String json) {
		Gson gson = new Gson();
		Form klasse = gson.fromJson(json, Form.class);
		return klasse;
	}
	
	public static String send(Form klasse) {
		Gson gson = new Gson();
		String json = gson.toJson(klasse);
		return json;
	}

}
