package gopherco.menu.configcreators.level;

import gopherco.configs.level.LevelConfig;
import gopherco.configs.level.Swarm;
import gopherco.menu.configcreators.ConfigMenuView;
import java.util.List;
import java.util.Set;

public class LevelMenuView extends ConfigMenuView<LevelConfig> {
    private static final String ADD_SWARM_INIT =
        "Enter a swarm as a string: string name, integer timeout, "
            + "integer interval, integer maxCalls separated by a single space";
    private static final String NUMBER_ARGS = "Enter exactly 4 arguments";
    private static final String NUMBER_FORMAT = "Incorrect number format";
    private static final String ADD_MAP_INIT = "Enter a map name from the ones below:";
    private static final String NO_SUCH_NAME = "No map with such name";

    @Override
    public void viewConfig(LevelConfig level) {
        System.out.println();
        System.out.println("Name: " + level.name());
        System.out.println("Map name: " + level.mapName());
        System.out.println("Waves: " + level.waves());
    }

    public void addSwarmInit() {
        System.out.println(ADD_SWARM_INIT);
    }

    public void viewFailedNumberOfArgs() {
        System.out.println(NUMBER_ARGS);
    }

    public void viewFailedNumberFormat() {
        System.out.println(NUMBER_FORMAT);
    }

    public void viewCurrentWave(List<Swarm> swarms) {
        for (var swarm : swarms) {
            System.out.println(swarm);
        }
    }

    public void viewAddMapNameInit() {
        System.out.println(ADD_MAP_INIT);
    }

    public void viewAvailableMapNames(Set<String> mapNames) {
        System.out.println(String.join(" ", mapNames));
    }

    public void viewNoSuchName() {
        System.out.println(NO_SUCH_NAME);
    }
}
