package server.entities;

import java.lang.reflect.Type;
import java.util.Date;

import javax.persistence.Entity;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

@Entity
public class ReplacementDeserializer implements JsonDeserializer<Replacement> {

	private static final long serialVersionUID = 1L;

	@Override
	public Replacement deserialize(JsonElement jsonEl, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		JsonObject json = jsonEl.getAsJsonObject();
		Replacement replacement = new Replacement();
		
		replacement.setDate(new Date(json.get("date").getAsString()));

		return replacement;
	}
}