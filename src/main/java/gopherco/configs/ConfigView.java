package gopherco.configs;

import gopherco.configs.map.MapConfig;
import java.util.List;

public class ConfigView {
    private static final String MAPS_ANNOUNCE = "Here are the maps you already have: ";

    private ConfigView() {
        throw new IllegalStateException();
    }

    public static void showMaps(List<MapConfig> maps) {
        System.out.println(MAPS_ANNOUNCE);
        for (var map : maps) {
            showMap(map);
        }
    }

    public static void showMap(MapConfig map) {
        System.out.println();
        System.out.println("Name: " + map.name());
        System.out.println("Color: " + map.backGroundColor());
        System.out.println("Path: " + map.path());
    }
}
