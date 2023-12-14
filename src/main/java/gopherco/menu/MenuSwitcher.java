package gopherco.menu;

import gopherco.io.console.UserChoiceProvider;
import gopherco.menu.context.map.ContextKey;
import gopherco.menu.context.map.MapContext;
import gopherco.menu.render.Renderer;
import java.util.NoSuchElementException;

public class MenuSwitcher {
    private static final String INVITATION = "Enter your choice: ";
    private final Menu initialMenu;
    private final Renderer renderer;
    private final UserChoiceProvider userChoiceProvider;
    private final MapContext mapContext;

    public MenuSwitcher(
        Menu initialMenu, Renderer renderer,
        UserChoiceProvider userChoiceProvider
    ) {
        this.initialMenu = initialMenu;
        this.renderer = renderer;
        this.userChoiceProvider = userChoiceProvider;
        this.mapContext = new MapContext();
    }

    public void doSwitch() {
        Menu currentMenu = mapContext.<Menu>get(ContextKey.CURRENT_MENU)
            .orElseGet(this::initMapContextWithInitialMenu);
        renderer.render(currentMenu);

        System.out.println(INVITATION);
        int userNumber;
        try {
            userNumber = userChoiceProvider.get();
        } catch (IllegalArgumentException iae) {
            System.err.println(iae.getMessage());
            return;
        }
        try {
            currentMenu.getItems()
                .stream()
                .filter(item -> item.getPosition() == userNumber)
                .findFirst()
                .get()
                .getItemHandler()
                .handle(mapContext);
        } catch (NoSuchElementException ignored) {
        }
        mapContext.<Menu>get(ContextKey.NEXT_MENU)
            .ifPresent(nextMenu -> mapContext.put(ContextKey.CURRENT_MENU, nextMenu));
        mapContext.remove(ContextKey.NEXT_MENU);
    }

    private Menu initMapContextWithInitialMenu() {
        mapContext.put(ContextKey.CURRENT_MENU, initialMenu);
        return initialMenu;
    }
}
