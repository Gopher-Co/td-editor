package gopherco.configs.level;

import java.util.List;

public record LevelConfig(String name, String mapName, List<Wave> waves) {
}
