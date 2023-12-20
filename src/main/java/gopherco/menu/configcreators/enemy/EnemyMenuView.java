package gopherco.menu.configcreators.enemy;

import gopherco.configs.enemy.EnemyConfig;
import gopherco.menu.configcreators.ConfigMenuView;
import java.util.Collection;

public class EnemyMenuView extends ConfigMenuView<EnemyConfig> {
    private static final String ENEMIES_ANNOUNCE = "Here are the enemies you already have: ";
    private static final String SUBMITTING_FAILED = "Couldn't add the enemy! Fill all the fields";
    private static final String DELETE_ENEMY_SUCCESS = "Map deleted";
    private static final String DELETE_ENEMY_FAILED = "Map never existed";
    private static final String NAME_INCORRECT = "Incorrect name field in the current config";
    private static final String MAX_HEALTH_INCORRECT = "Incorrect max health field in the current config";
    private static final String DAMAGE_INCORRECT = "Incorrect damage field in the current config";
    private static final String MONEY_AWARD_INCORRECT = "Incorrect money award field in the current config";
    private static final String ROOT_MEAN_SQUARE_SPEED_INCORRECT = "Incorrect RMS speed field in the current config";
    private static final String ADD_MAX_HEALTH_INIT = "Enter max health as an integer";
    private static final String NUMBER_FORMAT_INCORRECT = "Number format is incorrect";
    private static final String ADD_DAMAGE_INIT = "Enter damage as an integer";
    private static final String ROOT_MEAN_SQUARE_SPEED_INIT = "Enter RMS speed as a floating point number";
    private static final String ROOT_MONEY_AWARD_INIT = "Enter money award for killing the enemy as an integer";
    private static final String ADD_STRENGTH_INIT =
        "Enter a strength: 2 integer numbers (first for attack type, second for the value of its decrease) divided by a space";
    private static final String ADD_WEAKNESS_INIT =
        "Enter a strength: 2 integer numbers (first for attack type, second for the value of its decrease) divided by a space";
    private static final String NUMBER_ARGS = "Enter exactly 2 numbers";

    @Override
    public void viewConfig(EnemyConfig config) {
        System.out.println();
        System.out.println("Name: " + config.name());
        System.out.println("Max health: " + config.maxHealth());
        System.out.println("RMS of speed: " + config.rootMeanSquareSpeed());
        System.out.println("Money award for killing: " + config.moneyAward());
        System.out.println("Strengths: " + config.strengths());
        System.out.println("Weaknesses: " + config.weaknesses());
    }

    @Override
    public void viewConfigs(Collection<EnemyConfig> enemies) {
        System.out.println(ENEMIES_ANNOUNCE);
        for (var enemy : enemies) {
            viewConfig(enemy);
        }
    }

    @Override
    public void viewSubmittingFailed() {
        System.out.println(SUBMITTING_FAILED);
    }

    @Override
    public void viewDeleteConfigSuccess() {
        System.out.println(DELETE_ENEMY_SUCCESS);
    }

    @Override
    public void viewDeleteConfigFailed() {
        System.out.println(DELETE_ENEMY_FAILED);
    }

    public void viewNameIncorrect() {
        System.out.println(NAME_INCORRECT);
    }

    public void viewMaxHealthIncorrect() {
        System.out.println(MAX_HEALTH_INCORRECT);
    }

    public void viewDamageIncorrect() {
        System.out.println(DAMAGE_INCORRECT);
    }

    public void viewRootMeanSquareSpeedIncorrect() {
        System.out.println(ROOT_MEAN_SQUARE_SPEED_INCORRECT);
    }

    public void viewMoneyAwardIncorrect() {
        System.out.println(MONEY_AWARD_INCORRECT);
    }

    public void viewAddMaxHealthInit() {
        System.out.println(ADD_MAX_HEALTH_INIT);
    }

    public void viewNumberFormatIncorrect() {
        System.out.println(NUMBER_FORMAT_INCORRECT);
    }

    public void viewDamageInit() {
        System.out.println(ADD_DAMAGE_INIT);
    }

    public void viewRootMeanSquareSpeedInit() {
        System.out.println(ROOT_MEAN_SQUARE_SPEED_INIT);
    }

    public void viewMoneyAwardInit() {
        System.out.println(ROOT_MONEY_AWARD_INIT);
    }

    public void viewStrengthInit() {
        System.out.println(ADD_STRENGTH_INIT);
    }

    public void viewAddStrengthFailedNumberOfArgs() {
        System.out.println(NUMBER_ARGS);
    }

    public void viewWeaknessInit() {
        System.out.println(ADD_WEAKNESS_INIT);
    }

    public void viewAddWeaknessFailedNumberOfArgs() {
        System.out.println(NUMBER_ARGS);
    }
}
