package gopherco.configs.tower;

import gopherco.configs.NamedConfig;
import java.util.List;

public record TowerConfig(String name, List<Upgrade> upgrades, int price, int typeAttack, int initialDamage,
                          double initialRadius, int initialAttackSpeed, double initialProjectileSpeed,
                          String projectileName, int level) implements NamedConfig {
    @Override
    public String getName() {
        return name;
    }
}
