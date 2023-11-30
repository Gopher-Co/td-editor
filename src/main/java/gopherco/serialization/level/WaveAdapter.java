package gopherco.serialization.level;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import gopherco.configs.level.Swarm;
import gopherco.configs.level.Wave;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("MultipleStringLiterals")
public class WaveAdapter implements JsonSerializer<Wave>, JsonDeserializer<Wave> {
    @Override
    public Wave deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonArray swarmsJson = jsonObject.get("swarms").getAsJsonArray();
        List<Swarm> swarms = new ArrayList<>();
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Swarm.class, new SwarmAdapter());
        Gson gson = builder.create();
        for (var swarm : swarmsJson) {
            swarms.add(gson.fromJson(swarm, Swarm.class));
        }
        return new Wave(swarms);
    }

    @Override
    public JsonElement serialize(Wave wave, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        List<Swarm> swarms = wave.swarms();
        JsonArray swarmsJson = new JsonArray(swarms.size());
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Swarm.class, new SwarmAdapter());
        Gson gson = builder.create();
        for (var swarm : swarms) {
            swarmsJson.add(gson.toJsonTree(swarm));
        }
        jsonObject.add("swarms", swarmsJson);
        return jsonObject;
    }
}
