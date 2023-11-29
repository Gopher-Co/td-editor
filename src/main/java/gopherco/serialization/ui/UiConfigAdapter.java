package gopherco.serialization.ui;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import gopherco.configs.ui.UiConfig;
import java.awt.Color;
import java.lang.reflect.Type;
import static gopherco.serialization.util.SerializationTools.convertColorToString;

public class UiConfigAdapter implements JsonSerializer<UiConfig>, JsonDeserializer<UiConfig> {
    @Override
    public UiConfig deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject().get("colors").getAsJsonObject();
        return new UiConfig(
            Color.decode(jsonObject.get("MENU_BUTTON_PLAY_IMAGE").getAsString()),
            Color.decode(jsonObject.get("MENU_BUTTON_REPLAYS_IMAGE").getAsString()),
            Color.decode(jsonObject.get("MENU_BUTTON_EXIT_IMAGE").getAsString()),
            Color.decode(jsonObject.get("MENU_MAIN_IMAGE").getAsString()),
            Color.decode(jsonObject.get("MENU_BACKGROUND_IMAGE").getAsString()),
            Color.decode(jsonObject.get("MENU_LEFT_SIDEBAR_IMAGE").getAsString())
        );
    }

    @Override
    public JsonElement serialize(UiConfig uiConfig, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("MENU_BUTTON_PLAY_IMAGE", new JsonPrimitive(convertColorToString(uiConfig.menuButtonPlayColor())));
        jsonObject.add("MENU_BUTTON_REPLAYS_IMAGE", new JsonPrimitive(convertColorToString(uiConfig.menuButtonReplaysColor())));
        jsonObject.add("MENU_BUTTON_EXIT_IMAGE", new JsonPrimitive(convertColorToString(uiConfig.menuButtonExitColor())));
        jsonObject.add("MENU_MAIN_IMAGE", new JsonPrimitive(convertColorToString(uiConfig.menuMainColor())));
        jsonObject.add("MENU_BACKGROUND_IMAGE", new JsonPrimitive(convertColorToString(uiConfig.menuBackgroundColor())));
        jsonObject.add("MENU_LEFT_SIDEBAR_IMAGE", new JsonPrimitive(convertColorToString(uiConfig.menuLeftSidebarImage())));
        JsonObject uiConfigJson = new JsonObject();
        uiConfigJson.add("colors", jsonObject);
        return uiConfigJson;
    }
}
