package gopherco.serialization.level;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import gopherco.configs.level.Swarm;
import java.lang.reflect.Type;

@SuppressWarnings("MultipleStringLiterals")
public class SwarmAdapter implements JsonSerializer<Swarm>, JsonDeserializer<Swarm> {
    @Override
    public Swarm deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String enemyName = jsonObject.get("enemy_name").getAsString();
        int timeout = jsonObject.get("timeout").getAsInt();
        int interval = jsonObject.get("interval").getAsInt();
        int maxCalls = jsonObject.get("max_calls").getAsInt();
        return new Swarm(enemyName, timeout, interval, maxCalls);
    }

    @Override
    public JsonElement serialize(Swarm swarm, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("enemy_name", new JsonPrimitive(swarm.enemyName()));
        jsonObject.add("timeout", new JsonPrimitive(swarm.timeout()));
        jsonObject.add("interval", new JsonPrimitive(swarm.interval()));
        jsonObject.add("max_calls", new JsonPrimitive(swarm.maxCalls()));
        return jsonObject;
    }
}
