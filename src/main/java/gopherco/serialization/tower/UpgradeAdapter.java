package gopherco.serialization.tower;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import gopherco.configs.tower.Upgrade;
import java.lang.reflect.Type;

@SuppressWarnings("MultipleStringLiterals")
public class UpgradeAdapter implements JsonSerializer<Upgrade>, JsonDeserializer<Upgrade> {
    @Override
    public Upgrade deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        int price = jsonObject.get("price").getAsInt();
        int damageBoost = jsonObject.get("delta_damage").getAsInt();
        int attackSpeedBoost = jsonObject.get("delta_speed_attack").getAsInt();
        double radiusBoost = jsonObject.get("delta_radius").getAsDouble();
        String level = jsonObject.get("open_level").getAsString();
        return new Upgrade(price, damageBoost, attackSpeedBoost, radiusBoost, level);
    }

    @Override
    public JsonElement serialize(Upgrade upgrade, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("price", new JsonPrimitive(upgrade.price()));
        jsonObject.add("delta_damage", new JsonPrimitive(upgrade.damageBoost()));
        jsonObject.add("delta_speed_attack", new JsonPrimitive(upgrade.attackSpeedBoost()));
        jsonObject.add("delta_radius", new JsonPrimitive(upgrade.radiusBoost()));
        jsonObject.add("open_level", new JsonPrimitive(upgrade.level()));
        return jsonObject;
    }
}
