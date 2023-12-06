package gopherco.menu;

import gopherco.Configuration;
import gopherco.configs.map.MapConfig;
import gopherco.configs.map.Path;
import gopherco.configs.map.Point;
import gopherco.io.console.ConsoleInput;
import gopherco.io.console.StringInput;
import gopherco.menu.item.type.FunctionalItem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import static gopherco.configs.ConfigView.showMap;

public final class MapCreator extends Menu {
    private static final String SHOW_CURRENT_MAP = "Show the map being built";
    private static final String ADD_NEW_NAME = "Set name";
    private static final String ADD_NEW_COLOR = "Set color";
    private static final String APPEND_TO_PATH = "Append point to path";
    private static final String ADD_TO_START_OF_PATH = "Add first point to path";
    private static final String CLEAR_STATE = "Clear state";
    private static final String SUBMIT = "Submit config";
    private static final String FILE_REGEX = "\\w+";
    private final StringInput input;
    private final Configuration configuration;
    private List<Point> path;
    private Color color;
    private String name;

    public MapCreator(String title, Configuration configuration) {
        super(title);
        this.configuration = configuration;
        this.path = new ArrayList<>();
        this.input = new ConsoleInput();
    }

    public void addColor() {
        System.out.println("Enter a color as an integer");
        String colorInput = input.read();
        try {
            this.color = Color.decode(colorInput);
        } catch (NumberFormatException e) {
            System.out.println("Wrong color format");
        }
    }

    public void addName() {
        System.out.println("Enter a map name. Use latin letters, digits and underscore only");
        String nameInput = input.read();
        if (!nameInput.matches(FILE_REGEX)) {
            System.out.println("Incorrect file name. Use latin letters, digits and underscore only");
        } else if (configuration.existsMap(nameInput)) {
            System.out.println("Map name already occupied");
        } else {
            this.name = nameInput;
        }
    }

    private Point getPoint() {
        System.out.println("Enter a point: 2 floating point numbers divided by a space.");
        String[] numbers = input.read().trim().split(" ");
        if (numbers.length != 2) {
            System.out.println("Enter exactly 2 numbers");
            throw new IllegalStateException();
        }
        try {
            return new Point(Double.parseDouble(numbers[0]), Double.parseDouble(numbers[1]));
        } catch (NumberFormatException e) {
            System.out.println("Incorrect number format");
            throw new IllegalStateException();
        }
    }

    public void appendPoint() {
        try {
            Point point = getPoint();
            path.addLast(point);
        } catch (IllegalStateException ignored) {
        }
    }

    public void addFirstPoint() {
        try {
            Point point = getPoint();
            path.addFirst(point);
        } catch (IllegalStateException ignored) {
        }
    }

    public void clearState() {
        color = null;
        name = null;
        path = new ArrayList<>();
    }

    public void submit() {
        configuration.addMap(new MapConfig(name, color, new Path(path)));
        clearState();
    }

    public MapConfig getCurrentState() {
        return new MapConfig(name, color, new Path(path));
    }

    public static void setupMapCreationMenu(ItemInserter itemInserter, MapCreator mapCreationMenu) {
        itemInserter
            .add(new FunctionalItem(SHOW_CURRENT_MAP, context -> showMap(mapCreationMenu.getCurrentState())))
            .add(new FunctionalItem(ADD_NEW_NAME, context -> mapCreationMenu.addName()))
            .add(new FunctionalItem(ADD_NEW_COLOR, context -> mapCreationMenu.addColor()))
            .add(new FunctionalItem(APPEND_TO_PATH, context -> mapCreationMenu.appendPoint()))
            .add(new FunctionalItem(ADD_TO_START_OF_PATH, context -> mapCreationMenu.addFirstPoint()))
            .add(new FunctionalItem(CLEAR_STATE, context -> mapCreationMenu.clearState()))
            .add(new FunctionalItem(SUBMIT, context -> mapCreationMenu.submit()))
            .insert(mapCreationMenu)
            .clear();
    }
}
