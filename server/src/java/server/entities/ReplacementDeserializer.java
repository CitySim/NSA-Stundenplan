package server.entities;

import java.lang.reflect.Type;
import java.util.Date;

import javax.persistence.EntityManager;

import server.persistence.HibernateUtil;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class ReplacementDeserializer implements JsonDeserializer<Replacement> {

	@Override
	public Replacement deserialize(JsonElement jsonEl, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		JsonObject json = jsonEl.getAsJsonObject();
		Replacement replacement = new Replacement();
		EntityManager em = HibernateUtil.getEntityManager();
		
		replacement.setDate(new Date(json.get("date").getAsString()));
		replacement.setForm(em.find(Form.class, json.get("form").getAsInt()));
		replacement.setNote(json.get("note").getAsString());
		replacement.setRoom(em.find(Room.class, json.get("room").getAsInt()));
		replacement.setSubject(em.find (Subject.class, json.get("subject").getAsInt()));
		replacement.setTeacher(em.find(Teacher.class, json.get("teacher").getAsInt()));
		
		return replacement;
	}
}