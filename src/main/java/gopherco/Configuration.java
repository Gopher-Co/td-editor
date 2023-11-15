package gopherco;

import java.nio.file.Path;

public class Configuration {
    private static Configuration instance;
    private final Path pathToFiles = Path.of(System.getProperty("user.home"))
        .resolve("/gophers-td-editor");

    private Configuration() {
    }

    public static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

    public Path getRootPath() {
        return  pathToFiles;
    }
}
