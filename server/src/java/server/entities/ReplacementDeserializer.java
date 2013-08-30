package server.entities;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;

import javax.persistence.EntityManager;

import server.persistence.HibernateUtil;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class ReplacementDeserializer implements JsonDeserializer<Replacement> {

	@Override
	public Replacement deserialize(final JsonElement jsonEl, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
		final JsonObject json = jsonEl.getAsJsonObject();
		Replacement replacement;
		final EntityManager em = HibernateUtil.getEntityManager();
		if (json.get("id") != null && json.get("id").getAsInt() != 0) {
			replacement = em.find(Replacement.class, json.get("id").getAsInt());
		} else {
			replacement = new Replacement();
		}
		try {
			replacement.setDate(DateFormat.getDateInstance().parse((json.get("date").getAsString())));
		} catch (ParseException e) {
		}
		replacement.setForm(em.find(Form.class, json.get("form").getAsInt()));
		replacement.setNote(json.get("note").getAsString());
		replacement.setRoom(em.find(Room.class, json.get("room").getAsInt()));
		replacement.setSubject(em.find(Subject.class, json.get("subject").getAsInt()));
		replacement.setTeacher(em.find(Teacher.class, json.get("teacher").getAsInt()));
		replacement.setLesson(em.find(Lesson.class, json.get("lesson").getAsInt()));
		return replacement;
	}
}