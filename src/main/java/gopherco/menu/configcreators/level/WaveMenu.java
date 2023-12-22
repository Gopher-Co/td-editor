package gopherco.menu.configcreators.level;

import gopherco.configs.level.Swarm;
import gopherco.configs.level.Wave;
import gopherco.io.StringInput;
import gopherco.io.console.ConsoleInput;
import gopherco.menu.ItemInserter;
import gopherco.menu.Menu;
import gopherco.menu.item.type.FunctionalItem;
import java.util.ArrayList;
import java.util.List;

public class WaveMenu extends Menu {
    private static final String ADD_NEW_SWARM = "Add new swarm";
    private static final String SHOW_CURRENT_CONFIG = "Show the config being built";
    private static final String CLEAR_STATE = "Clear state";
    private static final String SUBMIT = "Submit config";
    private static final String FILE_REGEX = "\\w+";
    private final StringInput input;
    private final List<Wave> target;
    private List<Swarm> swarms;
    private final LevelMenuView view;

    public WaveMenu(String title, LevelMenuView view, List<Wave> target) {
        super(title);
        swarms = new ArrayList<>();
        this.view = view;
        input = new ConsoleInput();
        this.target = target;
    }

    @SuppressWarnings("MagicNumber")
    private void addSwarm() {
        view.addSwarmInit();
        String[] parameters = readInput().split(" +");
        if (parameters.length != 4) {
            view.viewFailedNumberOfArgs();
        } else if (!parameters[0].matches(FILE_REGEX)) {
            view.viewAddNameWrong();
            return;
        }
        try {
            swarms.add(new Swarm(
                parameters[0],
                Integer.parseInt(parameters[1]),
                Integer.parseInt(parameters[2]),
                Integer.parseInt(parameters[3])
            ));
        } catch (NumberFormatException e) {
            view.viewFailedNumberFormat();
        }
    }

    private void submit() {
        if (assertFieldsCorrect()) {
            target.add(new Wave(swarms));
            swarms = new ArrayList<>();
        } else {
            view.viewSubmittingFailed();
        }
    }

    private boolean assertFieldsCorrect() {
        return !swarms.isEmpty();
    }

    private void clearState() {
        swarms = new ArrayList<>();
    }

    private String readInput() {
        return input.read();
    }

    public void setUpMenu(ItemInserter itemInserter) {
        itemInserter
            .add(new FunctionalItem(ADD_NEW_SWARM, context -> addSwarm()))
            .add(new FunctionalItem(SHOW_CURRENT_CONFIG, context -> view.viewCurrentWave(swarms)))
            .add(new FunctionalItem(SUBMIT, context -> submit()))
            .add(new FunctionalItem(CLEAR_STATE, context -> clearState()))
            .insert(this)
            .clear();
    }
}
