package gopherco.menu.context;

import gopherco.configs.tower.TowerConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public final class ApplicationContext {
    private static ApplicationContext applicationContext = new ApplicationContext();
    private final AtomicBoolean running = new AtomicBoolean(true);
    private final List<TowerConfig> towerConfigs;

    public List<TowerConfig> getTowerConfigs() {
        return towerConfigs;
    }

    private ApplicationContext() {
        towerConfigs = new ArrayList<>();
    }

    public static ApplicationContext getInstance() {
        if (applicationContext == null) {
            applicationContext = new ApplicationContext();
        }
        return applicationContext;
    }

    public void shutdown() {
        running.set(false);
    }

    public boolean isRunning() {
        return running.get();
    }
}
