package gopherco.configs.enemy;

import gopherco.configs.NamedConfig;
import java.util.List;

public record EnemyConfig(String name, int maxHealth, int damage, double rootMeanSquareSpeed, int moneyAward,
                          List<Strength> strengths, List<Weakness> weaknesses) implements NamedConfig {
    @Override
    public String getName() {
        return name;
    }
}
