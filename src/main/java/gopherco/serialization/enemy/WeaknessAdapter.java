package gopherco.serialization.enemy;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import gopherco.configs.enemy.Weakness;
import java.lang.reflect.Type;

@SuppressWarnings("MultipleStringLiterals")
public class WeaknessAdapter implements JsonSerializer<Weakness>, JsonDeserializer<Weakness> {
    @Override
    public Weakness deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        int attackType = jsonObject.get("type").getAsInt();
        int damageIncrease = jsonObject.get("inc_dmg").getAsInt();
        return new Weakness(attackType, damageIncrease);
    }

    @Override
    public JsonElement serialize(Weakness weakness, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("type", new JsonPrimitive(weakness.attackType()));
        jsonObject.add("inc_dmg", new JsonPrimitive(weakness.damageIncrease()));
        return jsonObject;
    }
}
