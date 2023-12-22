package gopherco.configs.tower;

import gopherco.configs.NamedConfig;
import java.awt.Color;
import java.util.List;

public record TowerConfig(String name, List<Upgrade> upgrades, Integer price, Integer typeAttack, Integer initialDamage,
                          Double initialRadius, Integer initialAttackSpeed, Double initialProjectileSpeed,
                          Color projectileName, String level) implements NamedConfig {
    @Override
    public String getName() {
        return name;
    }
}
