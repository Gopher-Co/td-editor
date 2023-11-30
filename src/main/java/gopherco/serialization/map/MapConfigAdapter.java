package gopherco.serialization.map;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import gopherco.configs.map.MapConfig;
import gopherco.configs.map.Path;
import gopherco.configs.map.Point;
import java.awt.Color;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import static gopherco.serialization.util.SerializationTools.convertColorToString;

@SuppressWarnings("MultipleStringLiterals")
public class MapConfigAdapter implements JsonSerializer<MapConfig>, JsonDeserializer<MapConfig> {
    @Override
    public MapConfig deserialize(
        JsonElement jsonElement,
        Type type,
        JsonDeserializationContext jsonDeserializationContext
    ) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String name = jsonObject.get("name").getAsString();
        Color color = Color.decode(jsonObject.get("background_color").getAsString());
        JsonArray pathJson = jsonObject.getAsJsonArray("path");
        Path path = new Path(new ArrayList<>());
        for (var point : pathJson) {
            var pointObject = point.getAsJsonObject();
            path.addPoint(new Point(pointObject.get("x").getAsDouble(),
                pointObject.get("y").getAsDouble()));
        }
        return new MapConfig(name, color, path);
    }

    @Override
    public JsonElement serialize(MapConfig mapConfig, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("name", new JsonPrimitive(mapConfig.name()));
        jsonObject.add("background_color",
            new JsonPrimitive(convertColorToString(mapConfig.backGroundColor())));
        List<Point> path = mapConfig.path().path();
        JsonArray jsonArray = new
            JsonArray(path.size());
        for (Point point : path) {
            var object = new JsonObject();
            object.add("x", new JsonPrimitive(point.x()));
            object.add("y", new JsonPrimitive(point.y()));
            jsonArray.add(object);
        }
        jsonObject.add("path", jsonArray);
        return jsonObject;
    }
}
