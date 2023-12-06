package gopherco.menu.item;

import gopherco.menu.context.map.MapContext;

@FunctionalInterface
public interface ItemHandler {
    void handle(MapContext context);
}
