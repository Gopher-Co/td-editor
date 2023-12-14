package gopherco.menu.configcreators.map;

import gopherco.configs.map.MapConfig;
import gopherco.menu.configcreators.ConfigMenuView;
import java.util.Collection;

public class MapMenuView extends ConfigMenuView<MapConfig> {
    private static final String MAPS_ANNOUNCE = "Here are the maps you already have: ";
    private static final String ADD_COLOR_INIT = "Enter a color as an integer";
    private static final String ADD_COLOR_WRONG = "Wrong color format";
    private static final String ADD_NAME_INIT = "Enter a map name. Use latin letters, digits and underscore only";
    private static final String ADD_NAME_WRONG = "Incorrect file name. Use latin letters, digits and underscore only";
    private static final String ADD_NAME_OCCUPIED = "Map name already occupied";
    private static final String SUBMITTING_FAILED = "Couldn't add the map! Fill all the fields";
    private static final String TAKE_POINT_INIT = "Enter a point: 2 floating point numbers divided by a space";
    private static final String TAKE_POINT_ARGS = "Enter exactly 2 numbers";
    private static final String TAKE_POINT_FORMAT = TAKE_POINT_ARGS;
    private static final String DELETE_MAP_SUCCESS = "Map deleted";
    private static final String DELETE_MAP_FAILED = "Map never existed";
    private static final String COLOR_INCORRECT = "Incorrect color field in the current config";
    private static final String NAME_INCORRECT = "Incorrect name field in the current config";

    @Override
    public void viewConfigs(Collection<MapConfig> maps) {
        System.out.println(MAPS_ANNOUNCE);
        for (var map : maps) {
            viewConfig(map);
        }
    }

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

    public void viewAddNameInit() {
        System.out.println(ADD_NAME_INIT);
    }

    public void viewAddNameWrong() {
        System.out.println(ADD_NAME_WRONG);
    }

    public void viewAddNameOccupied() {
        System.out.println(ADD_NAME_OCCUPIED);
    }

    @Override
    public void viewSubmittingFailed() {
        System.out.println(SUBMITTING_FAILED);
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

    @Override
    public void viewDeleteConfigSuccess() {
        System.out.println(DELETE_MAP_SUCCESS);
    }

    @Override
    public void viewDeleteConfigFailed() {
        System.out.println(DELETE_MAP_FAILED);
    }

    public void viewColorIncorrect() {
        System.out.println(COLOR_INCORRECT);
    }

    public void viewNameIncorrect() {
        System.out.println(NAME_INCORRECT);
    }
}
