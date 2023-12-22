package gopherco.serialization.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gopherco.configs.enemy.EnemyConfig;
import gopherco.configs.level.LevelConfig;
import gopherco.configs.map.MapConfig;
import gopherco.configs.tower.TowerConfig;
import gopherco.configs.ui.UiConfig;
import gopherco.serialization.enemy.EnemyConfigAdapter;
import gopherco.serialization.level.LevelConfigAdapter;
import gopherco.serialization.map.MapConfigAdapter;
import gopherco.serialization.tower.TowerConfigAdapter;
import gopherco.serialization.ui.UiConfigAdapter;

public class EditorGson {
    private static Gson gson;

    private EditorGson() {
        throw new IllegalStateException();
    }

    private static void createGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(EnemyConfig.class, new EnemyConfigAdapter());
        builder.registerTypeAdapter(LevelConfig.class, new LevelConfigAdapter());
        builder.registerTypeAdapter(MapConfig.class, new MapConfigAdapter());
        builder.registerTypeAdapter(TowerConfig.class, new TowerConfigAdapter());
        builder.registerTypeAdapter(UiConfig.class, new UiConfigAdapter());
        builder.setPrettyPrinting();
        gson = builder.create();
    }

    public static Gson getInstance() {
        if (gson == null) {
            createGson();
        }
        return gson;
    }
}
