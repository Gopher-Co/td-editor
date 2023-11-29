package gopherco.serialization.util;

import java.awt.Color;

public class SerializationTools {
    private SerializationTools() {
        throw new IllegalStateException();
    }

    public static String convertColorToString(Color color) {
        return "#"+Integer.toHexString(color.getRGB()).substring(2);
    }
}
