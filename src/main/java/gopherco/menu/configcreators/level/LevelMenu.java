package gopherco.menu.configcreators.level;

import gopherco.Configuration;
import gopherco.configs.level.LevelConfig;
import gopherco.configs.level.Wave;
import gopherco.menu.ItemInserter;
import gopherco.menu.configcreators.ConfigMenu;
import gopherco.menu.item.type.ForthItem;
import gopherco.menu.item.type.FunctionalItem;
import java.util.ArrayList;
import java.util.List;

public class LevelMenu extends ConfigMenu<LevelConfig> {
    private static final String ADD_NEW_MAP_NAME = "Set map name";
    private static final String WAVE_MENU = "Create waves";
    private String mapName;
    private List<Wave> waves;
    private final WaveMenu waveMenu;

    public LevelMenu(String title, Configuration configuration) {
        super(title, configuration, configuration.getLevels(), new LevelMenuView());
        waves = new ArrayList<>();
        this.waveMenu = new WaveMenu(WAVE_MENU, (LevelMenuView) super.getView(), waves);
    }

    private void addMapName() {
        var view = getView();
        view.viewAddMapNameInit();
        var mapNames = getConfiguration().getMaps().keySet();
        view.viewAvailableMapNames(mapNames);
        String name = readInput();
        if (!mapNames.contains(name)) {
            view.viewNoSuchName();
        } else {
            mapName = name;
        }
    }

    @Override
    protected void clearState() {
        waves = new ArrayList<>();
    }

    @Override
    protected LevelConfig getCurrentState() {
        return new LevelConfig(name, mapName, waves);
    }

    @Override
    protected boolean assertFieldsCorrect() {
        return name != null && mapName != null && !waves.isEmpty();
    }

    @Override
    protected ItemInserter setupCreationMenuExtended(ItemInserter itemInserter) {
        return itemInserter
            .add(new FunctionalItem(ADD_NEW_MAP_NAME, context -> addMapName()))
            .add(new ForthItem(WAVE_MENU, waveMenu));
    }

    @Override
    public void setupCreationMenu(ItemInserter itemInserter) {
        super.setupCreationMenu(itemInserter);
        waveMenu.setUpMenu(itemInserter);
    }

    @Override
    public LevelMenuView getView() {
        return (LevelMenuView) super.getView();
    }
}
