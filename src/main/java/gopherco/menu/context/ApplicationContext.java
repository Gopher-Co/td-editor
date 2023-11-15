package gopherco.menu.context;

import java.util.concurrent.atomic.AtomicBoolean;

public final class ApplicationContext {
    private static ApplicationContext applicationContext = new ApplicationContext();
    private final AtomicBoolean running = new AtomicBoolean(true);

    private ApplicationContext() {}

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
