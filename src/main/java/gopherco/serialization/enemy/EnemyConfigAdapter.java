package gopherco.serialization.enemy;

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
import gopherco.configs.enemy.EnemyConfig;
import gopherco.configs.enemy.Strength;
import gopherco.configs.enemy.Weakness;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EnemyConfigAdapter implements JsonSerializer<EnemyConfig>, JsonDeserializer<EnemyConfig> {
    @Override
    public EnemyConfig deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String name = jsonObject.get("name").getAsString();
        int maxHealth = jsonObject.get("max_health").getAsInt();
        int damage = jsonObject.get("damage").getAsInt();
        double rootMeanSquareSpeed = jsonObject.get("vrms").getAsDouble();
        int moneyAward = jsonObject.get("money_award").getAsInt();
        JsonArray strengthsJson = jsonObject.getAsJsonArray("strengths");
        List<Strength> strengths = new ArrayList<>();
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Strength.class, new StrengthAdapter());
        builder.registerTypeAdapter(Weakness.class, new WeaknessAdapter());
        Gson gson = builder.create();
        for (var strength : strengthsJson) {
            var strengthObject = strength.getAsJsonObject();
            strengths.add(gson.fromJson(strengthObject, Strength.class));
        }
        JsonArray weaknessesJson = jsonObject.getAsJsonArray("weaknesses");
        List<Weakness> weaknesses = new ArrayList<>();
        for (var weakness : weaknessesJson) {
            var weaknessObject = weakness.getAsJsonObject();
            weaknesses.add(gson.fromJson(weaknessObject, Weakness.class));
        }
        return new EnemyConfig(name, maxHealth, damage, rootMeanSquareSpeed, moneyAward, strengths, weaknesses);
    }

    @Override
    public JsonElement serialize(EnemyConfig enemyConfig, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("name", new JsonPrimitive(enemyConfig.name()));
        jsonObject.add("max_health", new JsonPrimitive(enemyConfig.maxHealth()));
        jsonObject.add("damage", new JsonPrimitive(enemyConfig.damage()));
        jsonObject.add("vrms", new JsonPrimitive(enemyConfig.rootMeanSquareSpeed()));
        jsonObject.add("money_award", new JsonPrimitive(enemyConfig.moneyAward()));
        List<Strength> strengths = enemyConfig.strengths();
        JsonArray strengthsJson = new
            JsonArray(strengths.size());
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Strength.class, new StrengthAdapter());
        builder.registerTypeAdapter(Weakness.class, new WeaknessAdapter());
        Gson gson = builder.create();
        for (Strength strength : strengths) {
            strengthsJson.add(gson.toJsonTree(strength));
        }
        List<Weakness> weaknesses = enemyConfig.weaknesses();
        JsonArray weaknessesJson = new
            JsonArray(weaknesses.size());
        for (Weakness weakness : weaknesses) {
            weaknessesJson.add(gson.toJsonTree(weakness));
        }
        jsonObject.add("strengths", strengthsJson);
        jsonObject.add("weaknesses", weaknessesJson);
        return jsonObject;
    }
}
