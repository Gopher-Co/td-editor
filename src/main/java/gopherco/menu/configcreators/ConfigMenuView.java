package gopherco.menu.configcreators;

import gopherco.configs.NamedConfig;
import java.util.Collection;

public abstract class ConfigMenuView<T extends NamedConfig> {
    private static final String ADD_NAME_INIT = "Enter a config name. Use latin letters, digits and underscore only";
    private static final String ADD_NAME_WRONG = "Incorrect file name. Use latin letters, digits and underscore only";
    private static final String ADD_NAME_OCCUPIED = "Config name already occupied";

    public void viewAddNameInit() {
        System.out.println(ADD_NAME_INIT);
    }

    public void viewAddNameWrong() {
        System.out.println(ADD_NAME_WRONG);
    }

    public void viewAddNameOccupied() {
        System.out.println(ADD_NAME_OCCUPIED);
    }

    public abstract void viewConfig(T config);

    public abstract void viewConfigs(Collection<T> configs);

    public abstract void viewSubmittingFailed();

    public abstract void viewDeleteConfigSuccess();

    public abstract void viewDeleteConfigFailed();
}
