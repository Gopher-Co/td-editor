package gopherco.menu.configcreators.map;

import gopherco.configs.map.MapConfig;
import gopherco.menu.configcreators.ConfigMenuView;

public class MapMenuView extends ConfigMenuView<MapConfig> {
    private static final String ADD_COLOR_INIT = "Enter a color as an integer";
    private static final String ADD_COLOR_WRONG = "Wrong color format";
    private static final String TAKE_POINT_INIT = "Enter a point: 2 floating point numbers divided by a space";
    private static final String TAKE_POINT_ARGS = "Enter exactly 2 numbers";
    private static final String TAKE_POINT_FORMAT = TAKE_POINT_ARGS;
    private static final String COLOR_INCORRECT = "Incorrect color field in the current config";
    private static final String NAME_INCORRECT = "Incorrect name field in the current config";

    @Override
    public void viewConfig(MapConfig map) {
        System.out.println();
        System.out.println("Name: " + map.name());
        System.out.println("Color: " + map.backGroundColor());
        System.out.println("Path: " + map.path());
    }

    public void viewAddColorInit() {
        System.out.println(ADD_COLOR_INIT);
    }

    public void viewAddColorWrong() {
        System.out.println(ADD_COLOR_WRONG);
    }

    public void viewTakePointInit() {
        System.out.println(TAKE_POINT_INIT);
    }

    public void viewTakePointFailedNumberOfArgs() {
        System.out.println(TAKE_POINT_ARGS);
    }

    public void viewTakePointFailedNumberFormat() {
        System.out.println(TAKE_POINT_FORMAT);
    }

    public void viewColorIncorrect() {
        System.out.println(COLOR_INCORRECT);
    }

    public void viewNameIncorrect() {
        System.out.println(NAME_INCORRECT);
    }
}
