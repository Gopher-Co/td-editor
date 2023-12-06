package gopherco.configs.map;

import gopherco.configs.NamedConfig;
import java.awt.Color;

public record MapConfig(String name, Color backGroundColor, Path path) implements NamedConfig {
    @Override
    public String getName() {
        return name;
    }
}
