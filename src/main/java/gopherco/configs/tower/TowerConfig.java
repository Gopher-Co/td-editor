package gopherco.configs.tower;

import java.util.List;

public record TowerConfig(String name, List<UpgradeConfig> upgrades, int price, int typeAttack, int initialDamage,
                          double initialRadius, int initialAttackSpeed, double initialProjectileSpeed,  ProjectileConfig projectileConfig, int level) {
}
