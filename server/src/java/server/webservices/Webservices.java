package server.webservices;

import storage.entities.Klasse;

import com.google.gson.Gson;

public class Webservices {
	
	public static Klasse receive(String json) {
		Gson gson = new Gson();
		Klasse klasse = gson.fromJson(json, Klasse.class);
		return klasse;
	}
	
	public static String send(Klasse klasse) {
		Gson gson = new Gson();
		String json = gson.toJson(klasse);
		return json;
	}

}
