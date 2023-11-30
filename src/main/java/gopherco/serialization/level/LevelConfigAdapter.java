package gopherco.serialization.level;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import gopherco.configs.level.LevelConfig;
import gopherco.configs.level.Wave;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("MultipleStringLiterals")
public class LevelConfigAdapter implements JsonSerializer<LevelConfig>, JsonDeserializer<LevelConfig> {
    @Override
    public LevelConfig deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String name = jsonObject.get("level_name").getAsString();
        String mapName = jsonObject.get("map_name").getAsString();
        JsonArray wavesJson = jsonObject.get("game_rule").getAsJsonArray();
        List<Wave> waves = new ArrayList<>();
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Wave.class, new WaveAdapter());
        Gson gson = builder.create();
        for (var wave : wavesJson) {
            waves.add(gson.fromJson(wave, Wave.class));
        }
        return new LevelConfig(name, mapName, waves);
    }

    @Override
    public JsonElement serialize(LevelConfig levelConfig, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("level_name", new JsonPrimitive(levelConfig.name()));
        jsonObject.add("map_name", new JsonPrimitive(levelConfig.mapName()));
        List<Wave> waves = levelConfig.waves();
        JsonArray wavesJson = new
            JsonArray(waves.size());
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Wave.class, new WaveAdapter());
        Gson gson = builder.create();
        for (var wave : waves) {
            wavesJson.add(gson.toJsonTree(wave));
        }
        jsonObject.add("game_rule", wavesJson);
        return jsonObject;
    }
}
