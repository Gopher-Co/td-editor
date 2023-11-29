package gopherco.serialization.enemy;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import gopherco.configs.enemy.Strength;
import gopherco.configs.tower.Upgrade;
import java.lang.reflect.Type;

public class StrengthAdapter implements JsonSerializer<Strength>, JsonDeserializer<Strength> {
    @Override
    public Strength deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        int attackType = jsonObject.get("type").getAsInt();
        int damageDecrease = jsonObject.get("dec_dmg").getAsInt();
        return new Strength(attackType, damageDecrease);
    }

    @Override
    public JsonElement serialize(Strength strength, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("type", new JsonPrimitive(strength.attackType()));
        jsonObject.add("dec_dmg", new JsonPrimitive(strength.damageDecrease()));
        return jsonObject;
    }
}
