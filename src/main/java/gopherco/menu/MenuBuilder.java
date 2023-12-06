package gopherco.menu;

import gopherco.Configuration;
import gopherco.io.console.StringInput;
import gopherco.menu.context.ApplicationContext;
import gopherco.menu.item.type.ForthItem;
import gopherco.menu.item.type.FunctionalItem;
import gopherco.menu.item.type.ShutdownItem;
import static gopherco.configs.ConfigView.showMaps;
import static gopherco.menu.MapCreator.setupMapCreationMenu;

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
    private final StringInput input;
    private final Configuration configuration;

    public MenuBuilder(ApplicationContext applicationContext, StringInput input, Configuration configuration) {
        this.itemInserter = new ItemInserter(title -> new ShutdownItem(title, applicationContext));
        this.input = input;
        this.configuration = configuration;
    }

    public Menu build() {
        Menu mainMenu = new Menu(MAIN_MENU, true);
        Menu mapMenu = new Menu(MAP_MENU);
        MapCreator mapCreationMenu = new MapCreator(MAP_CREATION_MENU, configuration);
        setupMainMenu(mainMenu, mapMenu);
        setupMapMenu(mapMenu, mapCreationMenu);
        setupMapCreationMenu(itemInserter, mapCreationMenu);
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

    private void setupMapMenu(Menu mapMenu, Menu mapCreationMenu) {
        itemInserter
            .add(new FunctionalItem(SHOW_MAPS, context -> showMaps(configuration.getMaps())))
            .add(new ForthItem(MAP_CREATION_MENU, mapCreationMenu))
            .insert(mapMenu)
            .clear();
    }
}
