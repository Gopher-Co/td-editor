package gopherco;

import gopherco.io.console.ConsoleInput;
import gopherco.io.console.UserChoiceProvider;
import gopherco.menu.Menu;
import gopherco.menu.MenuBuilder;
import gopherco.menu.MenuSwitcher;
import gopherco.menu.context.ApplicationContext;
import gopherco.menu.render.MenuRenderer;

public class Application {
    private Application() {
        throw new IllegalStateException();
    }

    public static void main(String[] args) {
        Configuration configuration = Configuration.getInstance();
        ApplicationContext applicationContext = ApplicationContext.getInstance();
        ConsoleInput input = new ConsoleInput();
        MenuBuilder menuBuilder = new MenuBuilder(applicationContext, configuration);
        Menu mainMenu = menuBuilder.build();
        MenuRenderer renderer = new MenuRenderer();
        MenuSwitcher menuSwitcher = new MenuSwitcher(mainMenu, renderer, new UserChoiceProvider(input));
        while (applicationContext.isRunning()) {
            try {
                menuSwitcher.doSwitch();
            } catch (Exception e) {
                e.printStackTrace();
                ApplicationContext.getInstance().shutdown();
            }
        }
    }
}
