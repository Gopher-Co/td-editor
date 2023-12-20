package gopherco.menu.configcreators;

import gopherco.Configuration;
import gopherco.configs.NamedConfig;
import gopherco.io.StringInput;
import gopherco.io.console.ConsoleInput;
import gopherco.menu.ItemInserter;
import gopherco.menu.Menu;
import gopherco.menu.item.type.FunctionalItem;
import java.util.Map;

public abstract class ConfigMenu<T extends NamedConfig> extends Menu {
    private static final String ADD_NEW_NAME = "Set name";
    private static final String SHOW_CURRENT_CONFIG = "Show the config being built";
    private static final String CLEAR_STATE = "Clear state";
    private static final String SUBMIT = "Submit config";
    private static final String DELETE = "Delete in-memory config by name";
    private static final String FILE_REGEX = "\\w+";
    private final StringInput input;
    private final Configuration configuration;
    private final Map<String, T> configs;
    private final ConfigMenuView<T> view;
    protected String name;

    public ConfigMenu(String title, Configuration configuration, Map<String, T> configs, ConfigMenuView<T> view) {
        super(title);
        this.configuration = configuration;
        this.configs = configs;
        this.view = view;
        input = new ConsoleInput();
    }

    protected void addName() {
        var view = getView();
        view.viewAddNameInit();
        String nameInput = readInput();
        if (!nameInput.matches(FILE_REGEX)) {
            view.viewAddNameWrong();
        } else if (getConfiguration().getMaps().containsKey(nameInput)) {
            view.viewAddNameOccupied();
        } else {
            this.name = nameInput;
        }
    }

    protected void submit() {
        if (assertFieldsCorrect()) {
            T config = getCurrentState();
            configs.put(config.getName(), config);
            clearState();
        } else {
            view.viewSubmittingFailed();
        }
    }

    protected abstract void clearState();

    protected abstract T getCurrentState();

    protected void deleteConfig() {
        String name = input.read();
        if (!configs.containsKey(name)) {
            view.viewDeleteConfigFailed();
        } else {
            configs.remove(name);
            view.viewDeleteConfigSuccess();
        }
    }

    protected abstract boolean assertFieldsCorrect();

    public ConfigMenuView<T> getView() {
        return view;
    }

    protected String readInput() {
        return input.read();
    }

    protected Configuration getConfiguration() {
        return configuration;
    }

    public void setupCreationMenu(ItemInserter itemInserter) {
        itemInserter
            .add(new FunctionalItem(ADD_NEW_NAME, context -> addName()));
        setupCreationMenuExtended(itemInserter)
            .add(new FunctionalItem(
                SHOW_CURRENT_CONFIG,
                context -> view.viewConfig(getCurrentState())
            ))
            .add(new FunctionalItem(CLEAR_STATE, context -> clearState()))
            .add(new FunctionalItem(SUBMIT, context -> submit()))
            .add(new FunctionalItem(DELETE, context -> deleteConfig()))
            .insert(this)
            .clear();
    }

    protected abstract ItemInserter setupCreationMenuExtended(ItemInserter itemInserter);
}
