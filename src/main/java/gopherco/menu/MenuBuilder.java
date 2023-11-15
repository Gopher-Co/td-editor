package gopherco.menu;

import gopherco.Configuration;
import gopherco.io.console.StringInput;
import gopherco.menu.context.ApplicationContext;
import gopherco.menu.item.type.ShutdownItem;

public class MenuBuilder {
    private static final String MAIN_MENU = "Main menu";
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
        setupMainMenu(mainMenu);
        return mainMenu;
    }

    private void setupMainMenu(Menu mainMenu) {
        itemInserter.insert(mainMenu)
            .clear();
    }
}
