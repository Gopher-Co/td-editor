package gopherco.menu.configcreators.map;

import gopherco.Configuration;
import gopherco.configs.map.MapConfig;
import gopherco.configs.map.Path;
import gopherco.configs.map.Point;
import gopherco.menu.ItemInserter;
import gopherco.menu.configcreators.ConfigMenu;
import gopherco.menu.item.type.FunctionalItem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public final class MapMenu extends ConfigMenu<MapConfig> {
    private static final String ADD_NEW_NAME = "Set name";
    private static final String ADD_NEW_COLOR = "Set color";
    private static final String APPEND_TO_PATH = "Append point to path";
    private static final String ADD_TO_START_OF_PATH = "Add first point to path";
    private static final String FILE_REGEX = "\\w+";
    private List<Point> path;
    private Color color;
    private String name;

    public MapMenu(String title, Configuration configuration) {
        super(title, configuration, configuration.getMaps(), new MapMenuView());
        path = new ArrayList<>();
    }

    private void addColor() {
        var view = getView();
        view.viewAddColorInit();
        String colorInput = readInput();
        try {
            this.color = Color.decode(colorInput);
        } catch (NumberFormatException e) {
            view.viewAddColorWrong();
        }
    }

    private void addName() {
        var view = getView();
        view.viewAddNameInit();
        String nameInput = readInput();
        if (!nameInput.matches(FILE_REGEX)) {
            view.viewAddNameWrong();
        } else if (getConfiguration().existsMap(nameInput)) {
            view.viewAddNameOccupied();
        } else {
            this.name = nameInput;
        }
    }

    private Point takePoint() {
        var view = getView();
        view.viewTakePointInit();
        String[] numbers = readInput().split(" ");
        if (numbers.length != 2) {
            view.viewTakePointFailedNumberOfArgs();
            throw new IllegalStateException();
        }
        try {
            return new Point(Double.parseDouble(numbers[0]), Double.parseDouble(numbers[1]));
        } catch (NumberFormatException e) {
            view.viewTakePointFailedNumberFormat();
            throw new IllegalStateException();
        }
    }

    private void appendPointLast() {
        try {
            path.addLast(takePoint());
        } catch (IllegalStateException ignored) {
        }
    }

    private void addPointFirst() {
        try {
            path.addFirst(takePoint());
        } catch (IllegalStateException ignored) {
        }
    }

    protected void clearState() {
        color = null;
        name = null;
        path = new ArrayList<>();
    }

    @Override
    protected MapConfig getCurrentState() {
        return new MapConfig(name, color, new Path(path));
    }

    @Override
    protected boolean assertFieldsCorrect() {
        var view = getView();
        boolean correct = true;
        if (color == null) {
            view.viewColorIncorrect();
            correct = false;
        }
        if (name == null) {
            view.viewNameIncorrect();
            correct = false;
        }
        return correct;
    }

    @Override
    public MapMenuView getView() {
        return (MapMenuView) super.getView();
    }

    @Override
    protected ItemInserter setupCreationMenuExtended(ItemInserter itemInserter) {
        return itemInserter
            .add(new FunctionalItem(ADD_NEW_NAME, context -> addName()))
            .add(new FunctionalItem(ADD_NEW_COLOR, context -> addColor()))
            .add(new FunctionalItem(APPEND_TO_PATH, context -> appendPointLast()))
            .add(new FunctionalItem(ADD_TO_START_OF_PATH, context -> addPointFirst()));
    }
}
