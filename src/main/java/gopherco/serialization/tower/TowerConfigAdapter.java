package gopherco.serialization.tower;

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
import gopherco.configs.tower.ProjectileConfig;
import gopherco.configs.tower.TowerConfig;
import gopherco.configs.tower.UpgradeConfig;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TowerConfigAdapter implements JsonSerializer<TowerConfig>, JsonDeserializer<TowerConfig> {
    @Override
    public TowerConfig deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String name = jsonObject.get("name").getAsString();
        JsonArray upgradesJson = jsonObject.getAsJsonArray("upgrades");
        List<UpgradeConfig> upgrades = new ArrayList<>();
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(UpgradeConfig.class, new UpgradeConfigAdapter());
        Gson gson = builder.create();
        for (var upgrade : upgradesJson) {
            var upgradeObject = upgrade.getAsJsonObject();
            upgrades.add(gson.fromJson(upgradeObject, UpgradeConfig.class));
        }
        int price = jsonObject.get("price").getAsInt();
        int typeAttack = jsonObject.get("type").getAsInt();
        int initialDamage = jsonObject.get("initial_damage").getAsInt();
        double initialRadius = jsonObject.get("initial_radius").getAsDouble();
        int initialAttackSpeed = jsonObject.get("initial_speed_attack").getAsInt();
        double initialProjectileSpeed = jsonObject.get("init_projectile_speed").getAsInt();
        String projectileName = jsonObject
            .get("projectile_config").getAsJsonObject().get("name").getAsString();
        int level = jsonObject.get("open_level").getAsInt();
        return new TowerConfig(
            name,
            upgrades,
            price,
            typeAttack,
            initialDamage,
            initialRadius,
            initialAttackSpeed,
            initialProjectileSpeed,
            new ProjectileConfig(projectileName),
            level
        );
    }

    @Override
    public JsonElement serialize(TowerConfig towerConfig, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("name", new JsonPrimitive(towerConfig.name()));
        List<UpgradeConfig> upgrades = towerConfig.upgrades();
        JsonArray upgradesJson = new
            JsonArray(upgrades.size());
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(UpgradeConfig.class, new UpgradeConfigAdapter());
        Gson gson = builder.create();
        for (UpgradeConfig config : upgrades) {
            upgradesJson.add(gson.toJsonTree(config));
        }
        jsonObject.add("upgrades", upgradesJson);
        jsonObject.add("price", new JsonPrimitive(towerConfig.price()));
        jsonObject.add("type", new JsonPrimitive(towerConfig.typeAttack()));
        jsonObject.add("initial_damage", new JsonPrimitive(towerConfig.initialDamage()));
        jsonObject.add("initial_radius", new JsonPrimitive(towerConfig.initialRadius()));
        jsonObject.add("initial_speed_attack", new JsonPrimitive(towerConfig.initialAttackSpeed()));
        jsonObject.add("init_projectile_speed", new JsonPrimitive(towerConfig.initialProjectileSpeed()));
        JsonObject projectileConfig = new JsonObject();
        projectileConfig.add("name", new JsonPrimitive(towerConfig.projectileConfig().name()));
        jsonObject.add("projectile_config", projectileConfig);
        jsonObject.add("open_level", new JsonPrimitive(towerConfig.level()));
        return jsonObject;
    }
}
