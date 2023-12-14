package gopherco.menu.configcreators.ui;

import gopherco.configs.ui.UiConfig;
import gopherco.menu.configcreators.ConfigMenuView;
import java.util.Collection;

public class UiMenuView extends ConfigMenuView<UiConfig> {
    private static final String ADD_COLOR_INIT = "Enter a color as an integer";
    private static final String ADD_COLOR_WRONG = "Wrong color format";

    private static final String UI_ANNOUNCE = "Here is the UI you've already built: ";
    private static final String SUBMITTING_FAILED = "Couldn't add the ui! Fill all the fields";

    @Override
    public void viewConfig(UiConfig config) {
        if (config == null) {
            return;
        }
        System.out.println(UI_ANNOUNCE);
        System.out.println();
        System.out.println("Color of the button Play in the main menu: " + config.menuButtonPlayColor());
        System.out.println("Color of the button Replays in the main menu: " + config.menuButtonReplaysColor());
        System.out.println("Color of the button Exit in the main menu: " + config.menuButtonExitColor());
        System.out.println("Main color of the menu: " + config.menuMainColor());
        System.out.println("Background color of the menu: " + config.menuBackgroundColor());
        System.out.println("Color of the left sidebar in the main menu: " + config.menuLeftSidebarColor());
    }

    @Override
    public void viewConfigs(Collection<UiConfig> configs) {
        throw new IllegalStateException();
    }

    @Override
    public void viewSubmittingFailed() {
        System.out.println(SUBMITTING_FAILED);
    }

    @Override
    public void viewDeleteConfigSuccess() {
        throw new IllegalStateException();
    }

    @Override
    public void viewDeleteConfigFailed() {
        throw new IllegalStateException();
    }

    public void viewAddColorInit() {
        System.out.println(ADD_COLOR_INIT);
    }

    public void viewAddColorWrong() {
        System.out.println(ADD_COLOR_WRONG);
    }
}
