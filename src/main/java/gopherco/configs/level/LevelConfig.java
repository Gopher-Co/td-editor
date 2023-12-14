package gopherco.configs.level;

import gopherco.configs.NamedConfig;
import java.util.List;

public record LevelConfig(String name, String mapName, List<Wave> waves) implements NamedConfig {
    @Override
    public String getName() {
        return name;
    }
}
