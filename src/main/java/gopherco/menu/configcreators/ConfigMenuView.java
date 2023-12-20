package gopherco.menu.configcreators;

import gopherco.configs.NamedConfig;
import java.util.Collection;
import java.util.Set;

public abstract class ConfigMenuView<T extends NamedConfig> {
    private static final String ADD_NAME_INIT = "Enter a config name. Use latin letters, digits and underscore only";
    private static final String ADD_NAME_WRONG = "Incorrect file name. Use latin letters, digits and underscore only";
    private static final String ADD_NAME_OCCUPIED = "Config name already occupied";
    private static final String SUBMITTING_FAILED = "Couldn't add the config! Fill all the fields";
    private static final String CONFIGS_ANNOUNCE = "Here are the configs you already have: ";
    private static final String DELETE_CONFIG_SUCCESS = "Config deleted";
    private static final String DELETE_CONFIG_FAILED = "Config never existed";

    public abstract void viewConfig(T config);

    public void viewAddNameInit() {
        System.out.println(ADD_NAME_INIT);
    }

    public void viewAddNameWrong() {
        System.out.println(ADD_NAME_WRONG);
    }

    public void viewAddNameOccupied() {
        System.out.println(ADD_NAME_OCCUPIED);
    }

    public void viewConfigs(Collection<T> configs) {
        System.out.println(CONFIGS_ANNOUNCE);
        for (var config : configs) {
            viewConfig(config);
        }
    }

    public void viewSubmittingFailed() {
        System.out.println(SUBMITTING_FAILED);
    }

    public void viewDeleteConfigSuccess() {
        System.out.println(DELETE_CONFIG_SUCCESS);
    }

    public void viewDeleteConfigFailed() {
        System.out.println(DELETE_CONFIG_FAILED);
    }
}
