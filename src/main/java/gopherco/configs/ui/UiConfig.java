package gopherco.configs.ui;

import gopherco.configs.NamedConfig;
import java.awt.Color;

public record UiConfig(Color menuButtonPlayColor, Color menuButtonReplaysColor, Color menuButtonExitColor,
                       Color menuMainColor, Color menuBackgroundColor, Color menuLeftSidebarColor) implements
    NamedConfig {
    private static final String UI_NAME = "ui";

    @Override
    public String getName() {
        return UI_NAME;
    }
}
