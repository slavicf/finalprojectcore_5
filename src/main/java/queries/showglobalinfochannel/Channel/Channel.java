package queries.showglobalinfochannel.Channel;

import java.util.Arrays;

public class Channel {
    public Item[] items;

    @Override
    public String toString() {
        return "" + Arrays.toString(items);
    }
}
