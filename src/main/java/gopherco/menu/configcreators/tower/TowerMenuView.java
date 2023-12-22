package gopherco.menu.configcreators.tower;

import gopherco.configs.tower.TowerConfig;
import gopherco.menu.configcreators.ConfigMenuView;
import java.util.Set;
import static java.lang.System.lineSeparator;

public class TowerMenuView extends ConfigMenuView<TowerConfig> {
    private static final String ADD_PRICE_INIT = "Enter price as an integer";
    private static final String ADD_TYPE_ATTACK_INIT = "Enter type of attack as an integer value";
    private static final String ADD_INITIAL_DAMAGE_INIT = "Enter initial attack damage as an integer";
    private static final String ADD_INITIAL_RADIUS_INIT = "Enter initial radius value as a floating point number";
    private static final String ADD_INITIAL_ATTACK_SPEED_INIT = "Enter initial attack speed value as an integer";
    private static final String ADD_INITIAL_PROJECTILE_SPEED_INIT =
        "Enter initial projectile speed value as a floating point number";
    private static final String ADD_PROJECTILE_NAME_INIT = "Enter projectile color as an integer color code";
    private static final String ADD_UPGRADE_INIT =
        "Enter upgrade as 4 numbers and a string" + lineSeparator()
            + "int price, int damage boost, int attack speed boos, double radius boost, String level" + lineSeparator()
            + "string is either the name of one of the levels (look below) "
            + "or it's left empty if the upgrade should be open by default";
    private static final String ADD_UPGRADE_FAILED_NUMBER_OF_ARGS = "Enter exactly 5 values!";
    private static final String ADD_LEVEL_INIT =
        "Enter a level name from the list below or leave the string "
            + "blank if you want the upgrade to be opened by default";
    private static final String INCORRECT_LEVEL = "Incorrect level name";
    private static final String NUMBER_FORMAT = "Incorrect number format";

    @Override
    public void viewConfig(TowerConfig config) {
        System.out.println();
        System.out.println("Tower name: " + config.name());
        System.out.println("Upgrades: " + config.upgrades());
        System.out.println("Price: " + config.price());
        System.out.println("Attack type: " + config.typeAttack());
        System.out.println("Initial damage: " + config.initialDamage());
        System.out.println("Initial radius: " + config.initialRadius());
        System.out.println("Initial attack speed: " + config.initialAttackSpeed());
        System.out.println("Initial projectile speed: " + config.initialProjectileSpeed());
        System.out.println("Projectile color: " + config.projectileName());
        System.out.println("Opening level: " + config.level());
    }

    public void viewAddPriceInit() {
        System.out.println(ADD_PRICE_INIT);
    }

    public void viewNumberFormatIncorrect() {
        System.out.println(NUMBER_FORMAT);
    }

    public void viewAddTypeAttackInit() {
        System.out.println(ADD_TYPE_ATTACK_INIT);
    }

    public void viewAddInitialDamageInit() {
        System.out.println(ADD_INITIAL_DAMAGE_INIT);
    }

    public void viewAddInitialRadiusInit() {
        System.out.println(ADD_INITIAL_RADIUS_INIT);
    }

    public void viewAddInitialAttackSpeedInit() {
        System.out.println(ADD_INITIAL_ATTACK_SPEED_INIT);
    }

    public void viewAddInitialProjectileSpeedInit() {
        System.out.println(ADD_INITIAL_PROJECTILE_SPEED_INIT);
    }

    public void viewAddProjectileNameInit() {
        System.out.println(ADD_PROJECTILE_NAME_INIT);
    }

    public void viewAddUpgradeInit() {
        System.out.println(ADD_UPGRADE_INIT);
    }

    public void viewAddUpgradeFailedNumberOfArgs() {
        System.out.println(ADD_UPGRADE_FAILED_NUMBER_OF_ARGS);
    }

    public void viewAddLevelInit() {
        System.out.println(ADD_LEVEL_INIT);
    }

    public void viewAvailableLevelNames(Set<String> levelNames) {
        System.out.println(String.join(" ", levelNames));
    }

    public void viewIncorrectLevel() {
        System.out.println(INCORRECT_LEVEL);
    }
}
