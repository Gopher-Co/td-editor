package gopherco.menu;

import gopherco.Configuration;
import gopherco.menu.configcreators.enemy.EnemyMenu;
import gopherco.menu.configcreators.level.LevelMenu;
import gopherco.menu.configcreators.map.MapMenu;
import gopherco.menu.configcreators.tower.TowerMenu;
import gopherco.menu.configcreators.ui.UiMenu;
import gopherco.menu.context.ApplicationContext;
import gopherco.menu.item.type.ForthItem;
import gopherco.menu.item.type.FunctionalItem;
import gopherco.menu.item.type.ShutdownItem;

public class MenuBuilder {
    private static final String MAIN_MENU = "Main menu";
    private static final String MAP_MENU = "Build Maps";
    private static final String UI_MENU = "Build UI";
    private static final String ENEMY_MENU = "Build Enemies";
    private static final String LEVEL_MENU = "Build Levels";
    private static final String TOWER_MENU = "Build towers";
    private static final String MAP_CREATION_MENU = "Creating maps";
    private static final String UI_CREATION_MENU = "Creating ui";
    private static final String ENEMY_CREATION_MENU = "Creating enemies";
    private static final String LEVEL_CREATION_MENU = "Creating levels";
    private static final String TOWER_CREATION_MENU = "Creating towers";
    private static final String LOAD_CONFIGS = "Load existing configurations";
    private static final String CONFIGS_LOADED = "Configurations loaded";
    private static final String SAVE_CONFIGS = "Save configs to FS";
    private static final String CONFIGS_SAVED = "Configs saved";
    private static final String SHOW_MAPS = "Show in-memory maps";
    private static final String SHOW_UI = "Show in-memory ui";
    private static final String SHOW_ENEMIES = "Show in-memory enemies";
    private static final String SHOW_LEVELS = "Show in-memory levels";
    private static final String SHOW_TOWERS = "Show in-memory towers";
    private final ItemInserter itemInserter;
    private final Configuration configuration;

    public MenuBuilder(ApplicationContext applicationContext, Configuration configuration) {
        this.itemInserter = new ItemInserter(title -> new ShutdownItem(title, applicationContext));
        this.configuration = configuration;
    }

    public Menu build() {
        Menu mainMenu = new Menu(MAIN_MENU, true);
        Menu mapMenu = new Menu(MAP_MENU);
        MapMenu mapCreationMenu = new MapMenu(MAP_CREATION_MENU, configuration);
        Menu uiMenu = new Menu(UI_MENU);
        UiMenu uiCreationMenu = new UiMenu(UI_CREATION_MENU, configuration);
        Menu enemyMenu = new Menu(ENEMY_MENU);
        EnemyMenu enemyCreationMenu = new EnemyMenu(ENEMY_CREATION_MENU, configuration);
        Menu levelMenu = new Menu(LEVEL_MENU);
        LevelMenu levelCreationMenu = new LevelMenu(LEVEL_CREATION_MENU, configuration);
        Menu towerMenu = new Menu(TOWER_MENU);
        TowerMenu towerCreationMenu = new TowerMenu(TOWER_CREATION_MENU, configuration);
        setupMainMenu(mainMenu, mapMenu, uiMenu, enemyMenu, levelMenu, towerMenu);
        setupMapMenu(mapMenu, mapCreationMenu);
        setupUiMenu(uiMenu, uiCreationMenu);
        setupEnemyMenu(enemyMenu, enemyCreationMenu);
        setupLevelMenu(levelMenu, levelCreationMenu);
        setupTowerMenu(towerMenu, towerCreationMenu);
        mapCreationMenu.setupCreationMenu(itemInserter);
        uiCreationMenu.setupCreationMenu(itemInserter);
        enemyCreationMenu.setupCreationMenu(itemInserter);
        levelCreationMenu.setupCreationMenu(itemInserter);
        towerCreationMenu.setupCreationMenu(itemInserter);
        return mainMenu;
    }

    private void setupMainMenu(
        Menu mainMenu,
        Menu mapMenu,
        Menu uiMenu,
        Menu enemyMenu,
        Menu levelMenu,
        Menu towerMenu
    ) {
        itemInserter
            .add(new ForthItem(MAP_MENU, mapMenu))
            .add(new ForthItem(UI_MENU, uiMenu))
            .add(new ForthItem(ENEMY_MENU, enemyMenu))
            .add(new ForthItem(LEVEL_MENU, levelMenu))
            .add(new ForthItem(TOWER_MENU, towerMenu))
            .add(new FunctionalItem(SAVE_CONFIGS, context -> {
                configuration.saveEntities();
                System.out.println(CONFIGS_SAVED);
            }))
            .add(new FunctionalItem(LOAD_CONFIGS, context -> {
                configuration.loadEntities();
                System.out.println(CONFIGS_LOADED);
            }))
            .insert(mainMenu)
            .clear();
    }

    private void setupMapMenu(Menu mapMenu, MapMenu mapCreationMenu) {
        itemInserter
            .add(new FunctionalItem(
                SHOW_MAPS,
                context -> mapCreationMenu.getView().viewConfigs(configuration.getMaps().values())
            ))
            .add(new ForthItem(MAP_CREATION_MENU, mapCreationMenu))
            .insert(mapMenu)
            .clear();
    }

    private void setupUiMenu(Menu uiMenu, UiMenu uiCreationMenu) {
        itemInserter
            .add(new FunctionalItem(
                SHOW_UI,
                context -> uiCreationMenu.getView().viewConfig(configuration.getUi())
            ))
            .add(new ForthItem(UI_CREATION_MENU, uiCreationMenu))
            .insert(uiMenu)
            .clear();
    }

    private void setupEnemyMenu(Menu enemyMenu, EnemyMenu enemyCreationMenu) {
        itemInserter
            .add(new FunctionalItem(
                SHOW_ENEMIES,
                context -> enemyCreationMenu.getView().viewConfigs(configuration.getEnemies().values())
            ))
            .add(new ForthItem(ENEMY_CREATION_MENU, enemyCreationMenu))
            .insert(enemyMenu)
            .clear();
    }

    private void setupLevelMenu(Menu levelMenu, LevelMenu levelCreationMenu) {
        itemInserter
            .add(new FunctionalItem(
                SHOW_LEVELS,
                context -> levelCreationMenu.getView().viewConfigs(configuration.getLevels().values())
            ))
            .add(new ForthItem(LEVEL_CREATION_MENU, levelCreationMenu))
            .insert(levelMenu)
            .clear();
    }

    private void setupTowerMenu(Menu towerMenu, TowerMenu towerCreationMenu) {
        itemInserter
            .add(new FunctionalItem(
                SHOW_TOWERS,
                context -> towerCreationMenu.getView().viewConfigs(configuration.getTowers().values())
            ))
            .add(new ForthItem(TOWER_CREATION_MENU, towerCreationMenu))
            .insert(towerMenu)
            .clear();
    }
}
