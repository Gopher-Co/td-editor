package gopherco.configs.enemy;

import java.util.List;

public record EnemyConfig(String name, int maxHealth, int damage, double rootMeanSquareSpeed, int moneyAward,
                          List<Strength> strengths, List<Weakness> weaknesses) {
}
