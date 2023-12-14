package gopherco.menu.configcreators;

import gopherco.configs.NamedConfig;
import gopherco.configs.map.MapConfig;
import java.util.Collection;

public abstract class ConfigMenuView<T extends NamedConfig> {
    public abstract void viewConfig(T config);

    public abstract void viewConfigs(Collection<T> configs);

    public abstract void viewSubmittingFailed();

    public abstract void viewDeleteConfigSuccess();

    public abstract void viewDeleteConfigFailed();

}
