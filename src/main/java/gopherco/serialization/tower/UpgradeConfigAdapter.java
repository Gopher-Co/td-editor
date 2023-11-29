package gopherco.serialization.tower;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import gopherco.configs.tower.UpgradeConfig;
import java.lang.reflect.Type;

public class UpgradeConfigAdapter implements JsonSerializer<UpgradeConfig>, JsonDeserializer<UpgradeConfig> {
    @Override
    public UpgradeConfig deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        int price = jsonObject.get("price").getAsInt();
        int damageBoost = jsonObject.get("delta_damage").getAsInt();
        int attackSpeedBoost = jsonObject.get("delta_speed_attack").getAsInt();
        double radiusBoost = jsonObject.get("delta_radius").getAsDouble();
        int level = jsonObject.get("open_level").getAsInt();
        return new UpgradeConfig(price, damageBoost, attackSpeedBoost, radiusBoost, level);
    }

    @Override
    public JsonElement serialize(UpgradeConfig upgradeConfig, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("price", new JsonPrimitive(upgradeConfig.price()));
        jsonObject.add("delta_damage", new JsonPrimitive(upgradeConfig.damageBoost()));
        jsonObject.add("delta_speed_attack", new JsonPrimitive(upgradeConfig.attackSpeedBoost()));
        jsonObject.add("delta_radius", new JsonPrimitive(upgradeConfig.radiusBoost()));
        jsonObject.add("open_level", new JsonPrimitive(upgradeConfig.level()));
        return jsonObject;
    }
}
