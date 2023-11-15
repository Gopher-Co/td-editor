package gopherco.menu.item.type;

import gopherco.menu.item.Item;

public abstract class ReturnItem implements Item {

    protected final int position;
    protected final String title;

    public ReturnItem(int position, String title) {
        this.title = title;
        this.position = position;
    }

    public ReturnItem(String title) {
        this.title = title;
        this.position = 0;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getPosition() {
        return position;
    }

}
