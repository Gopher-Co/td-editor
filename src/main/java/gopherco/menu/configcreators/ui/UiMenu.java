package gopherco.menu.configcreators.ui;

import gopherco.Configuration;
import gopherco.configs.ui.UiConfig;
import gopherco.menu.ItemInserter;
import gopherco.menu.configcreators.ConfigMenu;
import gopherco.menu.item.type.FunctionalItem;
import java.awt.Color;

public class UiMenu extends ConfigMenu<UiConfig> {
    private static final String ADD_NEW_MENU_BUTTON_PLAY_COLOR = "Set color of the button Play in the main menu";
    private static final String ADD_NEW_MENU_BUTTON_REPLAYS_COLOR =
        "Set color of the button Replays in the main menu";
    private static final String ADD_NEW_MENU_BUTTON_EXIT_COLOR = "Set color of the button Exit in the main menu";
    private static final String ADD_NEW_MENU_MAIN_COLOR = "Set main color of the menu";
    private static final String ADD_NEW_MENU_BACKGROUND_COLOR = "Set background color of the menu";
    private static final String ADD_NEW_MENU_LEFT_SIDEBAR_COLOR = "Set color of the left sidebar in the main menu";

    private Color menuButtonPlayColor;
    private Color menuButtonReplaysColor;
    private Color menuButtonExitColor;
    private Color menuMainColor;
    private Color menuBackgroundColor;
    private Color menuLeftSidebarColor;

    public UiMenu(
        String title,
        Configuration configuration
    ) {
        super(title, configuration, null, new UiMenuView());
    }

    @Override
    protected void clearState() {
        menuButtonPlayColor = null;
        menuButtonReplaysColor = null;
        menuButtonExitColor = null;
        menuMainColor = null;
        menuBackgroundColor = null;
        menuLeftSidebarColor = null;
    }

    private void addMenuButtonPlayColor() {
        var view = getView();
        view.viewAddColorInit();
        String colorInput = readInput();
        try {
            this.menuButtonPlayColor = Color.decode(colorInput);
        } catch (NumberFormatException e) {
            view.viewAddColorWrong();
        }
    }

    private void addMenuButtonReplaysColor() {
        var view = getView();
        view.viewAddColorInit();
        String colorInput = readInput();
        try {
            this.menuButtonReplaysColor = Color.decode(colorInput);
        } catch (NumberFormatException e) {
            view.viewAddColorWrong();
        }
    }

    private void addMenuButtonExitColor() {
        var view = getView();
        view.viewAddColorInit();
        String colorInput = readInput();
        try {
            this.menuButtonExitColor = Color.decode(colorInput);
        } catch (NumberFormatException e) {
            view.viewAddColorWrong();
        }
    }

    private void addMenuMainColor() {
        var view = getView();
        view.viewAddColorInit();
        String colorInput = readInput();
        try {
            this.menuMainColor = Color.decode(colorInput);
        } catch (NumberFormatException e) {
            view.viewAddColorWrong();
        }
    }

    private void addMenuBackgroundColor() {
        var view = getView();
        view.viewAddColorInit();
        String colorInput = readInput();
        try {
            this.menuBackgroundColor = Color.decode(colorInput);
        } catch (NumberFormatException e) {
            view.viewAddColorWrong();
        }
    }

    private void addMenuLeftSidebarColor() {
        var view = getView();
        view.viewAddColorInit();
        String colorInput = readInput();
        try {
            this.menuLeftSidebarColor = Color.decode(colorInput);
        } catch (NumberFormatException e) {
            view.viewAddColorWrong();
        }
    }

    @Override
    protected UiConfig getCurrentState() {
        return new UiConfig(menuButtonPlayColor, menuButtonReplaysColor,
            menuButtonExitColor, menuMainColor, menuBackgroundColor, menuLeftSidebarColor
        );
    }

    @Override
    protected boolean assertFieldsCorrect() {
        return menuButtonPlayColor != null && menuButtonReplaysColor != null && menuButtonExitColor != null
            && menuMainColor != null && menuBackgroundColor != null && menuLeftSidebarColor != null;
    }

    @Override
    protected ItemInserter setupCreationMenuExtended(ItemInserter itemInserter) {
        return itemInserter
            .add(new FunctionalItem(ADD_NEW_MENU_BUTTON_PLAY_COLOR, context -> addMenuButtonPlayColor()))
            .add(new FunctionalItem(ADD_NEW_MENU_BUTTON_REPLAYS_COLOR, context -> addMenuButtonReplaysColor()))
            .add(new FunctionalItem(ADD_NEW_MENU_BUTTON_EXIT_COLOR, context -> addMenuButtonExitColor()))
            .add(new FunctionalItem(ADD_NEW_MENU_MAIN_COLOR, context -> addMenuMainColor()))
            .add(new FunctionalItem(ADD_NEW_MENU_BACKGROUND_COLOR, context -> addMenuBackgroundColor()))
            .add(new FunctionalItem(ADD_NEW_MENU_LEFT_SIDEBAR_COLOR, context -> addMenuLeftSidebarColor()));
    }

    @Override
    protected void submit() {
        if (assertFieldsCorrect()) {
            UiConfig config = getCurrentState();
            getConfiguration().addUi(config);
            clearState();
        } else {
            getView().viewSubmittingFailed();
        }
    }

    public UiMenuView getView() {
        return (UiMenuView) super.getView();
    }
}
