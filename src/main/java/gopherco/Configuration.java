package gopherco;

import gopherco.configs.enemy.EnemyConfig;
import gopherco.configs.level.LevelConfig;
import gopherco.configs.map.MapConfig;
import gopherco.configs.tower.TowerConfig;
import gopherco.configs.ui.UiConfig;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import static gopherco.serialization.util.SerializationTools.loadEnemies;
import static gopherco.serialization.util.SerializationTools.loadLevels;
import static gopherco.serialization.util.SerializationTools.loadMaps;
import static gopherco.serialization.util.SerializationTools.loadTowers;
import static gopherco.serialization.util.SerializationTools.loadUi;
import static gopherco.serialization.util.SerializationTools.saveEnemies;
import static gopherco.serialization.util.SerializationTools.saveLevels;
import static gopherco.serialization.util.SerializationTools.saveMaps;
import static gopherco.serialization.util.SerializationTools.saveTowers;
import static gopherco.serialization.util.SerializationTools.saveUi;
import static java.lang.System.getProperty;

public class Configuration {
    private static final String ENEMIES_FOLDER = "Enemies";
    private static final String LEVELS_FOLDER = "Levels";
    private static final String MAPS_FOLDER = "Maps";
    private static final String TOWERS_FOLDER = "Towers";
    private static final String UI_FOLDER = "UI";
    private static Configuration instance;
    private final Path pathToJar;
    private final Path pathToEnemies;
    private final Path pathToLevels;
    private final Path pathToMaps;
    private final Path pathToTowers;
    private final Path pathToUi;
    private final Map<String, EnemyConfig> enemies;
    private final Map<String, LevelConfig> levels;
    private final Map<String, MapConfig> maps;
    private final Map<String, TowerConfig> towers;
    private UiConfig ui;

    private Configuration() {
//        try {
//            pathToJar = Paths.get(Test.class.getProtectionDomain().getCodeSource().getLocation()
//                .toURI());
        pathToJar = Path.of(getProperty("user.dir"));
        pathToEnemies = pathToJar.resolve(ENEMIES_FOLDER);
        pathToLevels = pathToJar.resolve(LEVELS_FOLDER);
        pathToMaps = pathToJar.resolve(MAPS_FOLDER);
        pathToTowers = pathToJar.resolve(TOWERS_FOLDER);
        pathToUi = pathToJar.resolve(UI_FOLDER);
        enemies = new HashMap<>();
        levels = new HashMap<>();
        maps = new HashMap<>();
        towers = new HashMap<>();
//        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
//        }
    }

    public static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

    public Path getRootPath() {
        return pathToJar;
    }

    public void loadEntities() {
        enemies.putAll(loadEnemies(pathToEnemies));
        levels.putAll(loadLevels(pathToLevels));
        maps.putAll(loadMaps(pathToMaps));
        towers.putAll(loadTowers(pathToTowers));
        if (ui == null) {
            ui = loadUi(pathToUi);
        }
    }

    public void saveEntities() {
        saveEnemies(getEnemies().values(), pathToEnemies);
        saveLevels(getLevels().values(), pathToLevels);
        saveMaps(getMaps().values(), pathToMaps);
        saveTowers(getTowers().values(), pathToTowers);
        saveUi(getUi(), pathToUi);
    }

    public Map<String, EnemyConfig> getEnemies() {
        return enemies;
    }

    public Map<String, LevelConfig> getLevels() {
        return levels;
    }

    public Map<String, MapConfig> getMaps() {
        return maps;
    }

    public Map<String, TowerConfig> getTowers() {
        return towers;
    }

    public UiConfig getUi() {
        return ui;
    }

    public void addUi(UiConfig ui) {
        this.ui = ui;
    }
}
