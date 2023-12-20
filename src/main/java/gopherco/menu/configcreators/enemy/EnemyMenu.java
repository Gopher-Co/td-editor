package gopherco.menu.configcreators.enemy;

import gopherco.Configuration;
import gopherco.configs.enemy.EnemyConfig;
import gopherco.configs.enemy.Strength;
import gopherco.configs.enemy.Weakness;
import gopherco.menu.ItemInserter;
import gopherco.menu.configcreators.ConfigMenu;
import gopherco.menu.item.type.FunctionalItem;
import java.util.ArrayList;
import java.util.List;

public class EnemyMenu extends ConfigMenu<EnemyConfig> {
    private static final String ADD_NEW_MAX_HEALTH = "Set max health";
    private static final String ADD_NEW_DAMAGE = "Set damage";
    private static final String ADD_NEW_ROOT_MEAN_SQUARE_SPEED = "Set RMS speed";
    private static final String ADD_NEW_MONEY_AWARD = "Set money award";
    private static final String ADD_NEW_STRENGTH = "Add strength";
    private static final String ADD_NEW_WEAKNESS = "Add weakness";
    private Integer maxHealth;
    private Integer damage;
    private Double rootMeanSquareSpeed;
    private Integer moneyAward;
    private List<Strength> strengths;
    private List<Weakness> weaknesses;

    public EnemyMenu(String title, Configuration configuration) {
        super(title, configuration, configuration.getEnemies(), new EnemyMenuView());
        strengths = new ArrayList<>();
        weaknesses = new ArrayList<>();
    }

    private void addMaxHealth() {
        var view = getView();
        view.viewAddMaxHealthInit();
        String maxHealthInput = readInput();
        try {
            this.maxHealth = Integer.parseInt(maxHealthInput);
        } catch (NumberFormatException e) {
            view.viewNumberFormatIncorrect();
        }
    }

    private void addDamage() {
        var view = getView();
        view.viewDamageInit();
        String damageInput = readInput();
        try {
            this.damage = Integer.parseInt(damageInput);
        } catch (NumberFormatException e) {
            view.viewNumberFormatIncorrect();
        }
    }

    private void addRootMeanSquareSpeed() {
        var view = getView();
        view.viewRootMeanSquareSpeedInit();
        String rootMeanSquareSpeedInput = readInput();
        try {
            this.rootMeanSquareSpeed = Double.parseDouble(rootMeanSquareSpeedInput);
        } catch (NumberFormatException e) {
            view.viewNumberFormatIncorrect();
        }
    }

    private void addMoneyAward() {
        var view = getView();
        view.viewMoneyAwardInit();
        String moneyAward = readInput();
        try {
            this.moneyAward = Integer.parseInt(moneyAward);
        } catch (NumberFormatException e) {
            view.viewNumberFormatIncorrect();
        }
    }

    private void addStrength() {
        var view = getView();
        view.viewStrengthInit();
        String[] numbers = readInput().split(" ");
        if (numbers.length != 2) {
            view.viewAddStrengthFailedNumberOfArgs();
            throw new IllegalStateException();
        }
        try {
            strengths.add(new Strength(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1])));
        } catch (NumberFormatException e) {
            view.viewNumberFormatIncorrect();
            throw new IllegalStateException();
        }
    }

    private void addWeakness() {
        var view = getView();
        view.viewWeaknessInit();
        String[] numbers = readInput().split(" ");
        if (numbers.length != 2) {
            view.viewAddWeaknessFailedNumberOfArgs();
            throw new IllegalStateException();
        }
        try {
            strengths.add(new Strength(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1])));
        } catch (NumberFormatException e) {
            view.viewNumberFormatIncorrect();
            throw new IllegalStateException();
        }
    }

    @Override
    protected void clearState() {
        name = null;
        maxHealth = null;
        damage = null;
        rootMeanSquareSpeed = null;
        moneyAward = null;
        strengths = new ArrayList<>();
        weaknesses = new ArrayList<>();
    }

    @Override
    protected EnemyConfig getCurrentState() {
        return new EnemyConfig(name, maxHealth, damage, rootMeanSquareSpeed, moneyAward, strengths, weaknesses);
    }

    @Override
    protected boolean assertFieldsCorrect() {
        var view = getView();
        boolean correct = true;
        if (name == null) {
            view.viewNameIncorrect();
            correct = false;
        }
        if (maxHealth == null) {
            view.viewMaxHealthIncorrect();
            correct = false;
        }
        if (damage == null) {
            view.viewDamageIncorrect();
            correct = false;
        }
        if (rootMeanSquareSpeed == null) {
            view.viewRootMeanSquareSpeedIncorrect();
            correct = false;
        }
        if (moneyAward == null) {
            view.viewMoneyAwardIncorrect();
            correct = false;
        }
        return correct;
    }

    public EnemyMenuView getView() {
        return (EnemyMenuView) super.getView();
    }

    @Override
    protected ItemInserter setupCreationMenuExtended(ItemInserter itemInserter) {
        return itemInserter
            .add(new FunctionalItem(ADD_NEW_MAX_HEALTH, context -> addMaxHealth()))
            .add(new FunctionalItem(ADD_NEW_DAMAGE, context -> addDamage()))
            .add(new FunctionalItem(ADD_NEW_ROOT_MEAN_SQUARE_SPEED, context -> addRootMeanSquareSpeed()))
            .add(new FunctionalItem(ADD_NEW_MONEY_AWARD, context -> addMoneyAward()))
            .add(new FunctionalItem(ADD_NEW_STRENGTH, context -> addStrength()))
            .add(new FunctionalItem(ADD_NEW_WEAKNESS, context -> addWeakness()));
    }
}
