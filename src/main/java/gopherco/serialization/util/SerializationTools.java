package gopherco.serialization.util;

import com.google.gson.Gson;
import gopherco.configs.NamedConfig;
import gopherco.configs.enemy.EnemyConfig;
import gopherco.configs.level.LevelConfig;
import gopherco.configs.map.MapConfig;
import gopherco.configs.tower.TowerConfig;
import gopherco.configs.ui.UiConfig;
import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SerializationTools {
    private static final String GLOB_TEMPLATE = "*";
    private static final String ENEMIES_EXTENSION = ".lvl";
    private static final String LEVELS_EXTENSION = ".enm";
    private static final String MAPS_EXTENSION = ".map";
    private static final String TOWERS_EXTENSION = ".twr";
    private static final String UI_EXTENSION = ".ui";
    private static final String ENEMIES_GLOB = GLOB_TEMPLATE + ENEMIES_EXTENSION;
    private static final String LEVELS_GLOB = GLOB_TEMPLATE + LEVELS_EXTENSION;
    private static final String MAPS_GLOB = GLOB_TEMPLATE + MAPS_EXTENSION;
    private static final String TOWERS_GLOB = GLOB_TEMPLATE + TOWERS_EXTENSION;
    private static final String UI_GLOB = GLOB_TEMPLATE + UI_EXTENSION;

    private SerializationTools() {
        throw new IllegalStateException();
    }

    public static String convertColorToString(Color color) {
        return "#" + Integer.toHexString(color.getRGB()).substring(2);
    }

    public static <T> T loadEntity(Path file, Gson gson, Class<T> configClass) throws IOException {
        return gson.fromJson(Files.readString(file), configClass);
    }

    public static <T> List<T> loadEntities(Path pathToConfigs, String glob, Class<T> configClass) {
        List<T> result = new ArrayList<>();
        Gson gson = EditorGson.getInstance();
        try (var stream = Files.newDirectoryStream(pathToConfigs, glob)) {
            for (Path path : stream) {
                result.add(loadEntity(path, gson, configClass));
            }
        } catch (IOException ignored) {
        }
        return result;
    }

    public static List<EnemyConfig> loadEnemies(Path pathToConfigs) {
        return loadEntities(pathToConfigs, ENEMIES_GLOB, EnemyConfig.class);
    }

    public static List<LevelConfig> loadLevels(Path pathToConfigs) {
        return loadEntities(pathToConfigs, LEVELS_GLOB, LevelConfig.class);
    }

    public static List<MapConfig> loadMaps(Path pathToConfigs) {
        return loadEntities(pathToConfigs, MAPS_GLOB, MapConfig.class);
    }

    public static List<TowerConfig> loadTowers(Path pathToConfigs) {
        return loadEntities(pathToConfigs, TOWERS_GLOB, TowerConfig.class);
    }

    public static UiConfig loadUi(Path pathToConfigs) {
        List<UiConfig> configs = loadEntities(pathToConfigs, UI_GLOB, UiConfig.class);
        if (configs.size() != 1) {
            return null;
        } else {
            return configs.getFirst();
        }
    }

    public static void saveEntity(Path pathToConfigs, Gson gson, NamedConfig config, String extension) throws IOException {
        if (config == null) return;
        Path file = pathToConfigs.resolve(config.getName()).resolve(extension);
        Files.createFile(file);
        Files.writeString(file, gson.toJson(config));
    }

    public static void saveEntities(Path pathToConfigs, String extension, List<? extends NamedConfig> configs) throws IOException {
        Gson gson = EditorGson.getInstance();
        for (NamedConfig config : configs) {
            saveEntity(pathToConfigs, gson, config, extension);
        }
    }

    public static void saveEnemies(List<EnemyConfig> enemies, Path pathToConfigs) {
        try {
            saveEntities(pathToConfigs, ENEMIES_EXTENSION, enemies);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveLevels(List<LevelConfig> levels, Path pathToConfigs) {
        try {
            saveEntities(pathToConfigs, LEVELS_EXTENSION, levels);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveMaps(List<MapConfig> maps, Path pathToConfigs) {
        try {
            saveEntities(pathToConfigs, MAPS_EXTENSION, maps);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveTowers(List<TowerConfig> towers, Path pathToConfigs) {
        try {
            saveEntities(pathToConfigs, TOWERS_EXTENSION, towers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveUi(UiConfig ui, Path pathToConfigs) {
        try {
            saveEntities(pathToConfigs, UI_EXTENSION, List.of(ui));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
