package gopherco.menu.configcreators.tower;

import gopherco.Configuration;
import gopherco.configs.tower.TowerConfig;
import gopherco.configs.tower.Upgrade;
import gopherco.menu.ItemInserter;
import gopherco.menu.configcreators.ConfigMenu;
import gopherco.menu.item.type.FunctionalItem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class TowerMenu extends ConfigMenu<TowerConfig> {
    private static final String ADD_UPGRADE = "Add upgrade";
    private static final String ADD_PRICE = "Add price";
    private static final String ADD_ATTACK_TYPE = "Add attack type";
    private static final String ADD_INITIAL_DAMAGE = "Add initial damage";
    private static final String ADD_INITIAL_RADIUS = "Add initial radius";
    private static final String ADD_ATTACK_SPEED = "Add initial attack speed";
    private static final String ADD_PROJECTILE_SPEED = "Add initial projectile speed";
    private static final String ADD_COLOR = "Add projectile color";
    private static final String ADD_LEVEL = "Add level it's opened";
    private List<Upgrade> upgrades;
    Integer price;
    Integer typeAttack;
    Integer initialDamage;
    Double initialRadius;
    Integer initialAttackSpeed;
    Double initialProjectileSpeed;
    Color projectileName;
    String level;

    public TowerMenu(String title, Configuration configuration) {
        super(title, configuration, configuration.getTowers(), new TowerMenuView());
        upgrades = new ArrayList<>();
    }

    private void addPrice() {
        var view = getView();
        view.viewAddPriceInit();
        String priceInput = readInput();
        try {
            this.price = Integer.parseInt(priceInput);
        } catch (NumberFormatException e) {
            view.viewNumberFormatIncorrect();
        }
    }

    private void addTypeAttack() {
        var view = getView();
        view.viewAddTypeAttackInit();
        String typeAttackInput = readInput();
        try {
            this.typeAttack = Integer.parseInt(typeAttackInput);
        } catch (NumberFormatException e) {
            view.viewNumberFormatIncorrect();
        }
    }

    private void addInitialDamage() {
        var view = getView();
        view.viewAddInitialDamageInit();
        String initialDamageInput = readInput();
        try {
            this.initialDamage = Integer.parseInt(initialDamageInput);
        } catch (NumberFormatException e) {
            view.viewNumberFormatIncorrect();
        }
    }

    private void addInitialRadius() {
        var view = getView();
        view.viewAddInitialRadiusInit();
        String initialRadiusInput = readInput();
        try {
            this.initialRadius = Double.parseDouble(initialRadiusInput);
        } catch (NumberFormatException e) {
            view.viewNumberFormatIncorrect();
        }
    }

    private void addInitialAttackSpeed() {
        var view = getView();
        view.viewAddInitialAttackSpeedInit();
        String initialAttackSpeedInput = readInput();
        try {
            this.initialAttackSpeed = Integer.parseInt(initialAttackSpeedInput);
        } catch (NumberFormatException e) {
            view.viewNumberFormatIncorrect();
        }
    }

    private void addInitialProjectileSpeed() {
        var view = getView();
        view.viewAddInitialProjectileSpeedInit();
        String initialProjectileSpeedInput = readInput();
        try {
            this.initialProjectileSpeed = Double.parseDouble(initialProjectileSpeedInput);
        } catch (NumberFormatException e) {
            view.viewNumberFormatIncorrect();
        }
    }

    private void addProjectileName() {
        var view = getView();
        view.viewAddProjectileNameInit();
        String projectileNameInput = readInput();
        try {
            this.projectileName = Color.decode(projectileNameInput);
        } catch (NumberFormatException e) {
            view.viewNumberFormatIncorrect();
        }
    }

    @SuppressWarnings("MagicNumber")
    private void addUpgrade() {
        var view = getView();
        view.viewAddUpgradeInit();
        var levelNames = getConfiguration().getLevels().keySet();
        view.viewAvailableLevelNames(levelNames);
        String[] numbers = readInput().split(" +");
        String levelName;
        if (numbers.length < 4 || numbers.length > 5) {
            view.viewAddUpgradeFailedNumberOfArgs();
            return;
        } else if (numbers.length == 4) {
            levelName = "";
        } else {
            levelName = numbers[4];
        }
        try {
            if (!levelName.isEmpty() && !levelNames.contains(levelName)) {
                throw new IllegalArgumentException();
            }
            upgrades.add(new Upgrade(
                Integer.parseInt(numbers[0]),
                Integer.parseInt(numbers[1]),
                Integer.parseInt(numbers[2]),
                Double.parseDouble(numbers[3]),
                levelName
            ));
        } catch (NumberFormatException e) {
            view.viewNumberFormatIncorrect();
        } catch (IllegalArgumentException e) {
            view.viewIncorrectLevel();
            System.out.println(levelName);
        }
    }

    private void addLevel() {
        var view = getView();
        view.viewAddLevelInit();
        var levelNames = getConfiguration().getLevels().keySet();
        view.viewAvailableLevelNames(levelNames);
        String name = readInput();
        if (!levelNames.contains(name) && !name.isEmpty()) {
            view.viewIncorrectLevel();
        } else {
            this.level = name;
        }
    }

    @Override
    protected void clearState() {
        upgrades = new ArrayList<>();
        price = null;
        typeAttack = null;
        initialDamage = null;
        initialRadius = null;
        initialAttackSpeed = null;
        initialProjectileSpeed = null;
        projectileName = null;
        level = null;
    }

    @Override
    protected TowerConfig getCurrentState() {
        return new TowerConfig(
            name,
            upgrades,
            price,
            typeAttack,
            initialDamage,
            initialRadius,
            initialAttackSpeed,
            initialProjectileSpeed,
            projectileName,
            level
        );
    }

    @Override
    protected boolean assertFieldsCorrect() {
        return name != null && price != null && typeAttack != null && initialDamage != null && initialRadius != null
            && initialAttackSpeed != null && initialProjectileSpeed != null && projectileName != null && level != null;
    }

    @Override
    protected ItemInserter setupCreationMenuExtended(ItemInserter itemInserter) {
        return itemInserter
            .add(new FunctionalItem(ADD_UPGRADE, context -> addUpgrade()))
            .add(new FunctionalItem(ADD_PRICE, context -> addPrice()))
            .add(new FunctionalItem(ADD_ATTACK_TYPE, context -> addTypeAttack()))
            .add(new FunctionalItem(ADD_INITIAL_DAMAGE, context -> addInitialDamage()))
            .add(new FunctionalItem(ADD_INITIAL_RADIUS, context -> addInitialRadius()))
            .add(new FunctionalItem(ADD_ATTACK_SPEED, context -> addInitialAttackSpeed()))
            .add(new FunctionalItem(ADD_PROJECTILE_SPEED, context -> addInitialProjectileSpeed()))
            .add(new FunctionalItem(ADD_COLOR, context -> addProjectileName()))
            .add(new FunctionalItem(ADD_LEVEL, context -> addLevel()));
    }

    @Override
    public TowerMenuView getView() {
        return (TowerMenuView) super.getView();
    }
}
