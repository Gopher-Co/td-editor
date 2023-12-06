package gopherco;

import gopherco.configs.enemy.EnemyConfig;
import gopherco.configs.level.LevelConfig;
import gopherco.configs.map.MapConfig;
import gopherco.configs.tower.TowerConfig;
import gopherco.configs.ui.UiConfig;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
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
    private static final String enemiesFolder = "Enemies";
    private static final String levelsFolder = "Levels";
    private static final String mapsFolder = "Maps";
    private static final String towersFolder = "Towers";
    private static final String uiFolder = "UI";
    private static Configuration instance;
    private final Path pathToJar;
    private final Path pathToEnemies;
    private final Path pathToLevels;
    private final Path pathToMaps;
    private final Path pathToTowers;
    private final Path pathToUi;
    private final List<EnemyConfig> enemies;
    private final List<LevelConfig> levels;
    private final List<MapConfig> maps;
    private final List<TowerConfig> towers;
    private UiConfig ui;

    private Configuration() {
//        try {
//            pathToJar = Paths.get(Test.class.getProtectionDomain().getCodeSource().getLocation()
//                .toURI());
            pathToJar = Path.of(getProperty("user.dir"));
            pathToEnemies = pathToJar.resolve(enemiesFolder);
            pathToLevels = pathToJar.resolve(levelsFolder);
            pathToMaps = pathToJar.resolve(mapsFolder);
            pathToTowers = pathToJar.resolve(towersFolder);
            pathToUi = pathToJar.resolve(uiFolder);
            enemies = new ArrayList<>();
            levels = new ArrayList<>();
            maps = new ArrayList<>();
            towers = new ArrayList<>();
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
        enemies.addAll(loadEnemies(pathToEnemies));
        levels.addAll(loadLevels(pathToLevels));
        maps.addAll(loadMaps(pathToMaps));
        towers.addAll(loadTowers(pathToTowers));
        if (ui == null) {
            ui = loadUi(pathToUi);
        }
    }

    public void saveEntities() {
        saveEnemies(getEnemies(), pathToEnemies);
        saveLevels(getLevels(), pathToLevels);
        saveMaps(getMaps(), pathToMaps);
        saveTowers(getTowers(), pathToTowers);
        saveUi(getUi(), pathToUi);
    }

    public List<EnemyConfig> getEnemies() {
        return enemies;
    }

    public List<LevelConfig> getLevels() {
        return levels;
    }

    public List<MapConfig> getMaps() {
        return maps;
    }

    public List<TowerConfig> getTowers() {
        return towers;
    }

    public UiConfig getUi() {
        return ui;
    }

    public void addEnemy(EnemyConfig enemy) {
    }

    public void addLevel(LevelConfig level) {
    }

    public void addMap(MapConfig map) {
        maps.add(map);
    }

    public void addTower(TowerConfig tower) {
    }

    public void addUi(UiConfig ui) {
    }

    public boolean existsMap(String name) {
        return false;
    }

    public boolean existsEnemy(String name) {
        return false;
    }

    public boolean existsLevel(String name) {
        return false;
    }

    public boolean existsTower(String name) {
        return false;
    }
}
