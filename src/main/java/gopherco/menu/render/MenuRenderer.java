package gopherco.menu.render;

import gopherco.menu.Menu;
import gopherco.menu.item.Item;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class MenuRenderer implements Renderer {
    private static final Logger LOGGER = LogManager.getLogger();

    public void render(Menu menu) {
        String title = menu.getTitle();
        List<Item> items = menu.getItems();
        LOGGER.info("-----===[{}]===-----%n", title);
        for (int i = 0; i < items.size() - 1; i++) {
            printItem(i + 1, items.get(i));
        }
        Item returnOrExitItem = items.get(items.size() - 1);
        printItem(returnOrExitItem.getPosition(), returnOrExitItem);
    }

    private void printItem(int number, Item item) {
        LOGGER.info("{}. {}", number, item.getTitle());
    }
}
