package gopherco.menu;

import gopherco.Configuration;
import gopherco.menu.configcreators.map.MapMenu;
import gopherco.menu.context.ApplicationContext;
import gopherco.menu.item.type.ForthItem;
import gopherco.menu.item.type.FunctionalItem;
import gopherco.menu.item.type.ShutdownItem;

public class MenuBuilder {
    private static final String MAIN_MENU = "Main menu";
    private static final String MAP_MENU = "Build Maps";
    private static final String MAP_CREATION_MENU = "Creating maps";
    private static final String LOAD_CONFIGS = "Load existing configurations";
    private static final String CONFIGS_LOADED = "Configurations loaded";
    private static final String SAVE_CONFIGS = "Save configs to FS";
    private static final String CONFIGS_SAVED = "Configs saved";
    private static final String SHOW_MAPS = "Show in-memory maps";
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
        setupMainMenu(mainMenu, mapMenu);
        setupMapMenu(mapMenu, mapCreationMenu);
        mapCreationMenu.setupCreationMenu(itemInserter);
        return mainMenu;
    }

    private void setupMainMenu(Menu mainMenu, Menu mapMenu) {
        itemInserter
            .add(new ForthItem(MAP_MENU, mapMenu))
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
}
