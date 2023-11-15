package gopherco.menu.item.type;

import gopherco.menu.context.ApplicationContext;
import gopherco.menu.context.map.MapContext;
import gopherco.menu.item.Item;
import gopherco.menu.item.ItemHandler;
import gopherco.menu.item.handler.AbstractReturnItemHandler;

public class ShutdownItem extends ReturnItem {

    private final ApplicationContext applicationContext;

    public ShutdownItem(int position, String title, ApplicationContext applicationContext) {
        super(position, title);
        this.applicationContext = applicationContext;
    }

    public ShutdownItem(String title, ApplicationContext applicationContext) {
        super(title);
        this.applicationContext = applicationContext;
    }

    @Override
    public ItemHandler getItemHandler() {
        return new AbstractReturnItemHandler() {
            @Override
            protected void onExit(MapContext context) {
                applicationContext.shutdown();
            }
        };
    }

    @Override
    public Item copyWithPosition(int position) {
        return new ShutdownItem(position, title, applicationContext);
    }
}
